<template>
	<el-row class="border-1 border-solid p-[20px] leading-[32px] text-[var(--text-link-color)]">
		<div class="w-[50%]">
			<HotContentItem v-for="i of 5" :index="i" :title="`${titleList[i - 1]}`" />
		</div>
		<div class="w-[50%]">
			<HotContentItem v-for="i of Array.from({ length: 5 }, (_, i) => i + 6)" :index="i" :title="`${titleList[i - 1]}`" />
		</div>
	</el-row>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import HotContentItem from './HotContentItem.vue'

const titleList = [
	'“中国的明天必将更加美好”',
	'天安门广场国庆升旗仪式完整视频',
	'美国联邦政府正式“关门”',
	'这是今天响彻天安门广场的脚步声',
	'假期人都去哪儿了？直击各地旅游大军',
	'重温九三阅兵',
	'网警：双节欢乐游这些“陷阱”要绕行',
	'高速服务区全是“泡面人”',
	'中国正式成为安第斯共同体观察员国',
	'美国国务卿：祝愿中国人民健康幸福',
]
</script>

<style scoped lang="scss"></style>
