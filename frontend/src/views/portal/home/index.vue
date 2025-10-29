<template>
	<div class="w-[50%] m-auto">
		<div class="mt-[6%]">
			<MainTitle></MainTitle>
		</div>
		<div class="mt-[20px]">
			<SuperSearch :value="searchValue"></SuperSearch>
		</div>
		<div class="mt-[20px] flex justify-center">
			<LinkTitle></LinkTitle>
		</div>
		<div class="mt-[20px]">
			<HotContent></HotContent>
		</div>
	</div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import SuperSearch from '@/components/business/home/SuperSearch.vue'
import LinkTitle from '@/components/business/home/LinkTitle.vue'
import HotContent from '@/components/business/home/HotContent.vue'
import MainTitle from '@/components/business/home/MainTitle.vue'

const searchValue = ref('')
</script>

<style scoped lang="scss"></style>
