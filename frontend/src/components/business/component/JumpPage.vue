<template>
	<div class="flex justify-between">
		<el-button v-if="props.page.currentPage > 1">上一页</el-button>
		<el-button class="ml-auto" v-if="props.page.total > props.page.pageSize * props.page.currentPage">下一页</el-button>
	</div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

interface Page {
	pageSize: number
	currentPage: number
	total: number
}

const props = withDefaults(
	defineProps<{
		page: Page
	}>(),
	{
		page: () => ({}) as Page,
	},
)
</script>

<style scoped lang="scss"></style>
