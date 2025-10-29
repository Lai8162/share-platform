<template>
	<el-row :gutter="80">
		<el-col :span="8">
			<el-button>组件</el-button>
		</el-col>
		<el-col :span="8">
			<el-button>工具</el-button>
		</el-col>
		<el-col :span="8">
			<el-button>文章</el-button>
		</el-col>
	</el-row>
</template>

<script setup lang="ts">
import { ref } from 'vue'
</script>

<style scoped lang="scss"></style>
