<script setup lang="ts">
import { t } from '@/locales/i18n'

const props = withDefaults(
  defineProps<{
    title?: string
  }>(),
  {
    title: t('button.upload'),
  },
)
</script>

<template>
  <el-button icon="upload" type="primary" plain>
    {{ props.title }}
  </el-button>
</template>

<style scoped lang="scss"></style>
