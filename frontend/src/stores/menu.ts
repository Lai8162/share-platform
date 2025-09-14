import { ref } from 'vue'
import { defineStore } from 'pinia'

const useMenuStore = defineStore(
  'menu',
  () => {
    const menu = ref({ name: '全局菜单' } as Record<string, any>)

    return { menu }
  },
  {
    persist: true,
  },
)

export default useMenuStore
