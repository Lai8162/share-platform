import useUserStore from '@/stores/user'

export default {
  mounted(el: any, binding: any) {
    const { value } = binding
    const all_permission = '*:*:*'
    const permissions = useUserStore().permissions

    if (value && Array.isArray(value) && value.length > 0) {
      const permissionFlag = value

      const hasPermissions = permissions.some((permission) => {
        return all_permission === permission || permissionFlag.includes(permission)
      })

      if (!hasPermissions) {
        el.parentNode && el.parentNode.removeChild(el)
      }
    } else {
      throw new Error(`请设置操作权限标签值`)
    }
  },
}
