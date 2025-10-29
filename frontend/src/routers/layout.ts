import portal from './portal'

export default [
	{
		path: '/admin',
		name: 'admin',
		component: () => import('@/layouts/admin/Layout.vue'),
	},
	{
		path: '/portal',
		name: 'portal',
		component: () => import('@/layouts/portal/Layout.vue'),
		redirect: '/portal/home',
		children: portal,
	},
]
