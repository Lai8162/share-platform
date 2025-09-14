<script setup lang="ts">
import { t } from '@/locales/i18n'

const props = withDefaults(
  defineProps<{
    title?: string
  }>(),
  {
    title: t('button.download'),
  },
)
</script>

<template>
  <el-button icon="download" type="primary" plain>
    {{ props.title }}
  </el-button>
</template>

<style scoped lang="scss"></style>
