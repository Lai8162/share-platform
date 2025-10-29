<template>
	<div class="text-[var(rgb(115, 118, 122))] text-link">
		<span>{{ props.index }}</span>
		<el-icon></el-icon>
		<span>{{ props.title }}</span>
	</div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const props = withDefaults(
	defineProps<{
		index: number
		title: string
		link?: string
	}>(),
	{
		index: 0,
		title: '默认标题',
		link: '',
	},
)
</script>

<style scoped lang="scss"></style>
