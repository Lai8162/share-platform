<template>
  <el-button icon="plus" type="primary" plain>
    {{ props.title }}
  </el-button>
</template>

<script setup lang="ts">
import { t } from '@/locales/i18n'

const props = withDefaults(
  defineProps<{
    title?: string
  }>(),
  {
    title: t('button.add'),
  },
)
</script>

<style scoped lang="scss"></style>
