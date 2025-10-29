export default [
	{
		path: 'home',
		name: 'portal-home',
		component: () => import('@/views/portal/home/index.vue'),
	},
	{
		path: 'component',
		name: 'portal-component',
		component: () => import('@/views/portal/component/index.vue'),
	},
	{
		path: 'tool',
		name: 'portal-tool',
		component: () => import('@/views/portal/tool/index.vue'),
	},
	{
		path: 'article',
		name: 'portal-cpn',
		component: () => import('@/views/portal/article/index.vue'),
	},
	{
		path: 'feature',
		name: 'portal-feature',
		component: () => import('@/views/portal/feature/index.vue'),
	},
	{
		path: 'code',
		name: 'portal-code',
		component: () => import('@/views/portal/code/index.vue'),
	},
]
