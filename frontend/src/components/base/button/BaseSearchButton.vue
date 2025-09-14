<script setup lang="ts">
import { t } from '@/locales/i18n'

const props = withDefaults(
  defineProps<{
    title?: string
  }>(),
  {
    title: t('button.search'),
  },
)
</script>

<template>
  <el-button type="primary" icon="search">
    {{ props.title }}
  </el-button>
</template>

<style scoped lang="scss"></style>
