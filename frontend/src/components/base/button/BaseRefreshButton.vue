<script setup lang="ts">
import { t } from '@/locales/i18n'

const props = withDefaults(
  defineProps<{
    title?: string
  }>(),
  {
    title: t('button.refresh'),
  },
)
</script>

<template>
  <el-button plain icon="refresh">
    {{ props.title }}
  </el-button>
</template>

<style scoped lang="scss"></style>
