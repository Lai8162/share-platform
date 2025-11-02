<template>
	<div class="graph-container">
		<svg ref="svg" width="760" height="560" @click="handleSvgClick">
			<!-- 连接线 -->
			<template v-for="(node, index) in nodes">
				<line v-if="node.id !== 0" :key="'line-' + node.id" class="connection" :x1="centerNode.x" :y1="centerNode.y" :x2="node.x" :y2="node.y" />
			</template>

			<!-- 节点 -->
			<g v-for="node in nodes" :key="'node-' + node.id" class="node" @click="selectNode(node)">
				<circle :cx="node.x" :cy="node.y" :r="node.radius" :fill="getNodeColor(node)" stroke="white" stroke-width="1" />
				<text class="node-text" :x="node.x" :y="node.y" dy="0.3em" :title="node.name">
					{{ node.name }}
				</text>
			</g>
		</svg>
	</div>
</template>

<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'

const svg = ref(null)
const selectedNode = ref(null)

// 节点类型定义
const nodeTypes = {
	center: { color: 'url(#centerGradient)', name: '中心节点' },
	A: { color: '#ff6b6b', name: '类型 A' },
	B: { color: '#4ecdc4', name: '类型 B' },
	C: { color: '#ffe66d', name: '类型 C' },
}

// 初始节点数据
const nodes = ref([
	// 中心节点
	{ id: 0, x: 380, y: 280, radius: 20, name: '核心系统', type: 'center', description: '系统的核心组件，连接所有功能模块' },

	// 类型A节点 - 左上区域
	{ id: 1, x: 200, y: 150, radius: 15, name: '用户管理', type: 'A', description: '管理用户注册、登录和权限' },
	{ id: 2, x: 200, y: 100, radius: 15, name: '订单处理', type: 'A', description: '处理用户下单和支付流程' },
	{ id: 3, x: 200, y: 200, radius: 15, name: '数据分析', type: 'A', description: '收集和分析用户行为数据' },

	// 类型B节点 - 右上区域
	{ id: 4, x: 500, y: 150, radius: 15, name: '数据库', type: 'B', description: '存储系统所有数据' },
	{ id: 5, x: 500, y: 100, radius: 15, name: 'API网关', type: 'B', description: '处理所有外部请求' },
	{ id: 6, x: 500, y: 200, radius: 15, name: '缓存服务', type: 'B', description: '提高系统响应速度' },

	// 类型C节点 - 下方区域
	{ id: 7, x: 300, y: 400, radius: 15, name: '普通用户', type: 'C', description: '使用系统基本功能的用户' },
	{ id: 8, x: 300, y: 450, radius: 15, name: 'VIP用户', type: 'C', description: '享有高级特权的用户' },
	{ id: 9, x: 300, y: 500, radius: 15, name: '管理员', type: 'C', description: '管理系统后台的用户' },
] as any)

// 计算中心节点
const centerNode = computed(() => nodes.value.find((node) => node.id === 0))

// 获取节点颜色
const getNodeColor = (node) => {
	return node.id === 0 ? 'red' : nodeTypes[node.type].color
}

// 获取节点类型名称
const getNodeTypeName = (type) => {
	return nodeTypes[type].name
}

// 选择节点
const selectNode = (node) => {
	selectedNode.value = node
}

// 处理SVG点击事件（点击空白处取消选择）
const handleSvgClick = (event) => {
	if (event.target.tagName === 'svg') {
		selectedNode.value = null
	}
}
</script>

<style scoped lang="scss">
.container {
	max-width: 1200px;
	width: 100%;
	text-align: center;
}
header {
	margin-bottom: 30px;
}
.content {
	display: flex;
	flex-wrap: wrap;
	gap: 30px;
	justify-content: center;
}
.graph-container {
	background-color: rgba(255, 255, 255, 0.1);
	backdrop-filter: blur(10px);
	border-radius: 15px;
	padding: 20px;
	box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
	flex: 1;
	min-width: 300px;
	max-width: 800px;
	height: 600px;
	position: relative;
	overflow: hidden;
}
svg {
	border-radius: 15px;
	width: 100%;
	height: 100%;
}
.node {
	cursor: pointer;
	transition: all 0.3s ease;
}
.node-text {
	font-size: 6px;
	fill: black;
	text-anchor: middle;
	pointer-events: none;
	font-weight: bold;
}
.connection {
	stroke: black;
	stroke-width: 0.5;
}
</style>
