<template>
	<el-container>
		<el-header><Header /></el-header>
		<el-main><Content /></el-main>
		<el-footer><Footer /></el-footer>
	</el-container>
</template>

<script setup lang="ts">
import Content from './Content.vue'
import Header from './Header.vue'
import Footer from './Footer.vue'
</script>

<style scoped lang="scss">
.el-container {
	min-height: 100vh;
}
.el-header {
	height: 60px;
	background: var(--header-color);
}
.el-main {
	flex: 1;
	min-height: calc(100vh - 140px);
}
.el-footer {
	height: 40px;
	--el-footer-padding: 0 0;
	background-color: var(--footer-color);
}
</style>
