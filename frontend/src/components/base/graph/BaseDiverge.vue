<template>
	<div class="content">
		<div class="graph-container">
			<canvas ref="canvas" :width="width" :height="height"></canvas>
		</div>
	</div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'

const canvas = ref<any>(null)
const selectedNode = ref<any>(null)
let ctx = null as any
const width = ref(1920)
const height = ref(900)
const coreRadius = ref(20)
const otherRadius = ref(10)

// 初始化图表
onMounted(() => {
	ctx = canvas.value.getContext('2d')
	ctx.imageSmoothingEnabled = true
	drawGraph()

	// 添加点击事件监听
	canvas.value.addEventListener('click', handleCanvasClick)
})

// 节点类型定义
const nodeTypes = {
	A: { color: '#ff6b6b', name: '类型 A', description: '产品功能相关节点' },
	B: { color: '#4ecdc4', name: '类型 B', description: '技术组件相关节点' },
	C: { color: '#ffe66d', name: '类型 C', description: '用户群体相关节点' },
} as any

// 初始节点数据
const nodes = ref([
	// 中心节点
	{ id: 0, x: width.value / 2, y: height.value / 2 - coreRadius.value / 2, radius: coreRadius.value, name: '核心系统', type: 'center', description: '系统的核心组件，连接所有功能模块' },

	// 类型A节点
	{ id: 1, x: width.value / 2 - 200, y: height.value - 720, radius: otherRadius.value, name: '用户管理', type: 'A', description: '管理用户注册、登录和权限' },
	{ id: 2, x: width.value / 2 - 200, y: height.value - 700, radius: otherRadius.value, name: '订单处理', type: 'A', description: '处理用户下单和支付流程' },
	{ id: 3, x: width.value / 2 - 200, y: height.value - 680, radius: otherRadius.value, name: '数据分析', type: 'A', description: '收集和分析用户行为数据' },

	// 类型B节点
	{ id: 4, x: width.value / 2 + 400, y: height.value - 720, radius: otherRadius.value, name: '数据库', type: 'B', description: '存储系统所有数据' },
	{ id: 5, x: width.value / 2 + 400, y: height.value - 700, radius: otherRadius.value, name: 'API网关', type: 'B', description: '处理所有外部请求' },
	{ id: 6, x: width.value / 2 + 400, y: height.value - 680, radius: otherRadius.value, name: '缓存服务', type: 'B', description: '提高系统响应速度' },
	{ id: 4, x: width.value / 2 + 400, y: height.value - 660, radius: otherRadius.value, name: '数据库', type: 'B', description: '存储系统所有数据' },
	{ id: 5, x: width.value / 2 + 400, y: height.value - 640, radius: otherRadius.value, name: 'API网关', type: 'B', description: '处理所有外部请求' },
	{ id: 6, x: width.value / 2 + 400, y: height.value - 620, radius: otherRadius.value, name: '缓存服务', type: 'B', description: '提高系统响应速度' },

	// 类型C节点
	{ id: 7, x: width.value / 2 - 600, y: height.value - 200, radius: otherRadius.value, name: '普通用户', type: 'C', description: '使用系统基本功能的用户' },
	{ id: 8, x: width.value / 2 - 600, y: height.value - 280, radius: otherRadius.value, name: 'VIP用户', type: 'C', description: '享有高级特权的用户' },
	{ id: 9, x: width.value / 2 - 600, y: height.value - 250, radius: otherRadius.value, name: '管理员', type: 'C', description: '管理系统后台的用户' },
])

// 绘制节点
const drawNode = (node: any) => {
	ctx.beginPath()
	ctx.arc(node.x, node.y, node.radius, 0, Math.PI * 2)

	if (node.type === 'center') {
		// 中心节点特殊样式
		const gradient = ctx.createRadialGradient(node.x, node.y, 0, node.x, node.y, node.radius)
		gradient.addColorStop(0, '#8e2de2')
		gradient.addColorStop(1, '#4a00e0')
		ctx.fillStyle = gradient
	} else {
		ctx.fillStyle = nodeTypes[node.type].color
	}

	ctx.fill()

	// 节点边框
	ctx.strokeStyle = 'black'
	ctx.lineWidth = 0.5
	ctx.stroke()

	// 节点文字
	ctx.fillStyle = 'black'
	ctx.font = '8px Arial'
	ctx.textAlign = 'center'
	ctx.textBaseline = 'middle'
	ctx.fillText(node.name, node.x, node.y)
}

// 绘制连接线
const drawConnection = (node1: any, node2: any) => {
	ctx.beginPath()
	ctx.moveTo(node1.x, node1.y)
	ctx.lineTo(node2.x, node2.y)
	ctx.strokeStyle = nodeTypes[node2.type].color
	ctx.lineWidth = 1
	ctx.stroke()
}

// 绘制整个图表
const drawGraph = () => {
	if (!ctx) return

	// 清空画布
	ctx.clearRect(0, 0, canvas.value.width, canvas.value.height)

	// 绘制连接线
	const centerNode = nodes.value[0]
	nodes.value.forEach((node) => {
		if (node.id !== 0) {
			drawConnection(centerNode, node)
		}
	})

	// 绘制节点
	nodes.value.forEach((node) => {
		drawNode(node)
	})
}

// 处理节点点击
const handleCanvasClick = (event: any) => {
	const rect = canvas.value.getBoundingClientRect()
	const x = event.clientX - rect.left
	const y = event.clientY - rect.top

	// 检查是否点击了节点
	for (const node of nodes.value) {
		const distance = Math.sqrt((x - node.x) ** 2 + (y - node.y) ** 2)
		if (distance <= node.radius) {
			selectedNode.value = node
			return
		}
	}

	selectedNode.value = null
}
</script>

<style scoped lang="scss">
.container {
	width: 100%;
	text-align: center;
}
header {
	margin-bottom: 30px;
}
h1 {
	font-size: 2.5rem;
	margin-bottom: 10px;
	text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);
}
.subtitle {
	font-size: 1.2rem;
	opacity: 0.9;
}
.content {
	display: flex;
	flex-wrap: wrap;
	gap: 30px;
	justify-content: center;
	padding: 15px;
}
.graph-container {
	background-color: rgba(255, 255, 255, 0.1);
	backdrop-filter: blur(10px);
	border-radius: 15px;
	padding: 20px;
	box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
	flex: 1;
	min-width: 300px;
	max-width: 1920px;
	height: 900px;
	position: relative;
	overflow: hidden;
}
.controls {
	background-color: rgba(255, 255, 255, 0.1);
	backdrop-filter: blur(10px);
	border-radius: 15px;
	padding: 20px;
	box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
	width: 300px;
	display: flex;
	flex-direction: column;
	gap: 20px;
}
.legend {
	display: flex;
	flex-direction: column;
	gap: 10px;
}
.legend-item {
	display: flex;
	align-items: center;
	gap: 10px;
	padding: 8px 12px;
	border-radius: 8px;
	background-color: rgba(255, 255, 255, 0.1);
}
.color-box {
	width: 20px;
	height: 20px;
	border-radius: 50%;
}
.node-info {
	background-color: rgba(255, 255, 255, 0.1);
	padding: 15px;
	border-radius: 10px;
	margin-top: 10px;
}
.node-info h3 {
	margin-bottom: 10px;
	color: #ffcc00;
}
button {
	background: linear-gradient(135deg, #6a11cb, #2575fc);
	color: white;
	border: none;
	padding: 12px 20px;
	border-radius: 8px;
	cursor: pointer;
	font-size: 1rem;
	transition: all 0.3s ease;
	box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
}
button:hover {
	transform: translateY(-2px);
	box-shadow: 0 6px 20px rgba(0, 0, 0, 0.3);
}
.instructions {
	margin-top: 30px;
	background-color: rgba(255, 255, 255, 0.1);
	padding: 20px;
	border-radius: 15px;
	max-width: 800px;
}
.instructions h2 {
	margin-bottom: 15px;
	color: #ffcc00;
}
.instructions ul {
	text-align: left;
	margin-left: 20px;
}
.instructions li {
	margin-bottom: 8px;
}
canvas {
	border-radius: 10px;
}
</style>
