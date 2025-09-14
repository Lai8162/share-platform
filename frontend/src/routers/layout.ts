export default [
  {
    path: '/lr',
    component: () => import('@/layouts/left-right/Layout.vue'),
  },
  {
    path: '/tb',
    component: () => import('@/layouts/top-bottom/Layout.vue'),
  },
]
