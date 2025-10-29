<template>
	<div class="text-center text-[var(--title-color)] text-[28px]">
		<span>{{ title }}</span>
	</div>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const title = import.meta.env.VITE_APP_TITLE
</script>

<style scoped lang="scss"></style>
