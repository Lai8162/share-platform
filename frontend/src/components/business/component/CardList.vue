<template>
	<div class="grid grid-cols-5 gap-[20px] text-[14px]">
		<Card v-for="item in list" :component="item"></Card>
	</div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import Card from './Card.vue'

const list = [
	{ name: '卡片', content: '<a href="#">1111</a href="#">', viewCount: 1012, collectCount: 233 },
	{ name: '卡片', content: '<a href="#">1111</a href="#">', viewCount: 1012, collectCount: 233 },
	{ name: '卡片', content: '<a href="#">1111</a href="#">', viewCount: 1012, collectCount: 233 },
	{ name: '卡片', content: '<a href="#">1111</a href="#">', viewCount: 1012, collectCount: 233 },
	{ name: '卡片', content: '<a href="#">1111</a href="#">', viewCount: 1012, collectCount: 233 },
	{ name: '卡片', content: '<a href="#">1111</a href="#">', viewCount: 1012, collectCount: 233 },
	{ name: '卡片', content: '<a href="#">1111</a href="#">', viewCount: 1012, collectCount: 233 },
	{ name: '卡片', content: '<a href="#">1111</a href="#">', viewCount: 1012, collectCount: 233 },
	{ name: '卡片', content: '<a href="#">1111</a href="#">', viewCount: 1012, collectCount: 233 },
	{ name: '卡片', content: '<a href="#">1111</a href="#">', viewCount: 1012, collectCount: 233 },
	{ name: '卡片', content: '<a href="#">1111</a href="#">', viewCount: 1012, collectCount: 233 },
	{ name: '卡片', content: '<a href="#">1111</a href="#">', viewCount: 1012, collectCount: 233 },
]
</script>

<style scoped lang="scss"></style>
