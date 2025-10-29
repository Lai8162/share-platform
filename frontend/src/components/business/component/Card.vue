<template>
	<div>
		<div class="flex justify-center items-center min-w-[200px] min-h-[180px] border-solid border-1 rounded-[8px]">
			<div v-html="component?.content"></div>
		</div>
		<div class="flex mt-[5px]">
			<div>{{ component?.name }}</div>
			<div class="flex ml-auto">
				<div class="text-[var(--el-color-info-dark-2)] text-[13px]">{{ component?.viewCount }} views</div>
				<div class="flex items-center ml-[12px] text-[13px]">
					<BaseSvgIcon name="collect" color="gray"></BaseSvgIcon>
					<span>{{ component?.collectCount }}</span>
				</div>
			</div>
		</div>
	</div>
</template>

<script setup lang="ts">
import { toRefs } from 'vue'
import BaseSvgIcon from '@/components/base/svg-icon/BaseSvgIcon.vue'

interface Component {
	name: string
	type?: number
	viewCount?: number
	collectCount?: number
	content: string
	creator?: string
}

const props = withDefaults(
	defineProps<{
		component: Component
	}>(),
	{
		component: Object,
	},
)
const { component } = toRefs(props)
</script>

<style scoped lang="scss"></style>
