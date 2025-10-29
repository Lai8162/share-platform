<template>
	<BaseDialog width="25%" title="创建">
		<el-form ref="ruleFormRef" :model="ruleForm" :rules="rules" label-width="auto" label-position="top">
			<el-form-item label="标题" prop="name">
				<el-input v-model="ruleForm.name" />
			</el-form-item>
			<el-form-item label="类型" prop="type">
				<el-select v-model="ruleForm.type" placeholder="" clearable>
					<el-option label="vue" :value="1" />
					<el-option label="其它" :value="2" />
				</el-select>
			</el-form-item>
			<el-form-item label="内容" prop="content">
				<el-input v-model="ruleForm.content" type="textarea" :rows="5" />
			</el-form-item>
		</el-form>
	</BaseDialog>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'

import BaseDialog from '@/components/base/dialog/BaseDialog.vue'

import type { FormInstance, FormRules } from 'element-plus'

interface RuleForm {
	name: string
	type: number
	content: string
}

const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive<RuleForm>({
	name: '',
	type: 1,
	content: '',
})

const rules = reactive<FormRules<RuleForm>>({
	name: { required: true, message: '标题不能为空', trigger: 'blur' },
	type: { required: true, message: '类型不能为空', trigger: 'change' },
	content: { required: true, message: '内容不能为空', trigger: 'blur' },
})

const submitForm = async (formEl: FormInstance | undefined) => {
	if (!formEl) return
	await formEl.validate((valid, fields) => {
		if (valid) {
			console.log('submit!')
		} else {
			console.log('error submit!', fields)
		}
	})
}

const resetForm = (formEl: FormInstance | undefined) => {
	if (!formEl) return
	formEl.resetFields()
}

function add() {
	
}
</script>

<style scoped lang="scss"></style>
