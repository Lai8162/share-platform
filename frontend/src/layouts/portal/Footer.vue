<template>
	<el-row justify="center" class="leading-[40px] text-[12px]">{{ copyRight }}</el-row>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const copyRight = ref(`Copyright © share platform ${new Date().getFullYear()}`)
</script>

<style scoped lang="scss"></style>
