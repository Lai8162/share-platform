<template>
	<el-row>
		<el-col :span="3"></el-col>
		<el-col :span="17">
			<el-menu :default-active="activeIndex" router mode="horizontal" @select="handleSelect">
				<el-menu-item index="/portal/home">首页</el-menu-item>
				<el-menu-item index="/portal/component">组件</el-menu-item>
				<el-menu-item index="/portal/tool">工具</el-menu-item>
				<el-menu-item index="/portal/article">文章</el-menu-item>
				<el-menu-item index="/portal/code">码上分享</el-menu-item>
				<el-menu-item index="/portal/feature">特性风向标</el-menu-item>
			</el-menu>
		</el-col>
		<el-col :span="4"></el-col>
	</el-row>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const activeIndex = ref(router.currentRoute.value.fullPath)
const handleSelect = (key: string, keyPath: string[]) => {
	console.log(key, keyPath)
}
</script>

<style scoped lang="scss">
.el-menu {
	background-color: var(--header-color);
}
</style>
