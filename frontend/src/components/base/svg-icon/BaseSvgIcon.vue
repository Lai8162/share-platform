<template>
  <svg aria-hidden="true" :fill="color" :style="`width:${props.size}; height:${props.size}`">
    <use :xlink:href="symbolId" />
  </svg>
</template>

<script setup lang="ts">
import { computed } from 'vue'

const props = withDefaults(
  defineProps<{
    name: string
    color?: string
    size?: string
  }>(),
  {
    name: '',
    color: 'gray',
    size: '1rem',
  },
)

const symbolId = computed(() => `#icon-${props.name}`)
</script>
