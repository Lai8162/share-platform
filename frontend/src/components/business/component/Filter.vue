<template>
	<div class="flex">
		<div>
			<el-button>Button</el-button>
			<el-button>Input</el-button>
			<el-button>Card</el-button>
			<el-button>Form</el-button>
		</div>
		<div class="flex gap-[20px] ml-auto min-w-[350px] max-w-[550px]">
			<el-button type="primary" icon="Plus" @click="addVisible">创建</el-button>
			<el-input placeholder="请输入组件名称" v-model="name">
				<template #append>
					<el-button icon="Search" />
				</template>
			</el-input>
		</div>
		<AddForm v-model="addForm"></AddForm>
	</div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import AddForm from './AddForm.vue'

const name = ref('')
const type = ref()
const button = ref(false)
const input = ref(false)
const card = ref(false)
const form = ref(false)
// 新增表单显示
const addForm = ref(false)

function addVisible() {
	addForm.value = true
}
</script>

<style scoped lang="scss"></style>
