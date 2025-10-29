<template>
	<el-input v-model="value" placeholder="请输入" class="input-with-select">
		<template #prepend>
			<el-select v-model="type" placeholder="" style="width: 80px">
				<el-option label="全部" value="all" />
				<el-option label="组件" value="1" />
				<el-option label="工具" value="2" />
				<el-option label="特性" value="3" />
				<el-option label="文章" value="4" />
			</el-select>
		</template>
		<template #append>
			<el-button>
				<template #icon>
					<el-icon :size="20"><Search /></el-icon>
				</template>
			</el-button>
		</template>
	</el-input>
</template>

<script setup lang="ts">
import { toRefs } from 'vue'
import { ref } from 'vue'

const type = ref('all')

const props = withDefaults(
	defineProps<{
		value: string
		height?: string
	}>(),
	{
		height: '50px',
	},
)
const { height, value } = toRefs(props)

defineExpose({
	value,
	type,
})
</script>

<style scoped lang="scss">
:deep(.el-select__wrapper) {
	min-height: v-bind(height);
}
:deep(.el-input-group__append) {
	.el-button {
		padding: 8px 20px;
	}
}
</style>
