<template>
	<div class="w-[70%] m-auto">
		<Filter class="mt-[40px]"></Filter>
		<CardList class="mt-[40px] text-[var(--text-base-color)]"></CardList>
		<JumpPage class="mt-[20px]" :page="page"></JumpPage>
	</div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import CardList from '@/components/business/component/CardList.vue'
import JumpPage from '@/components/business/component/JumpPage.vue'
import Filter from '@/components/business/component/Filter.vue'

const page = {
	currentPage: 1,
	pageSize: 15,
	total: 17,
}
</script>

<style scoped lang="scss"></style>
