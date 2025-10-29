import home from './home'
import layout from './layout'
import { createRouter, createWebHistory } from 'vue-router'

const routes = [...home, ...layout]

const router = createRouter({
	history: createWebHistory(),
	routes,
})

export default router
