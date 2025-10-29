<template>
	<el-dialog v-bind="$attrs" v-model="visible">
		<slot></slot>
		<template #header>
			{{ $attrs.title }}
		</template>
		<div slot="header"></div>
		<div slot="footer" class="flex justify-center">
			<el-button @click="((visible = false), emit('confirm'))">取 消</el-button>
			<el-button type="primary" @click="((visible = false), emit('cancel'))">确 定</el-button>
		</div>
	</el-dialog>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const visible = defineModel()

const emit = defineEmits(['confirm', 'cancel'])
</script>

<style scoped lang="scss"></style>
