import { t } from '@/locales/i18n'
import useUserStore from '@/stores/user'

export default {
  mounted(el: any, binding: any) {
    const { value } = binding
    const super_admin = 'admin'
    const roles = useUserStore().roles

    if (value && Array.isArray(value) && value.length > 0) {
      const theRoleArray = value

      const hasRole = roles.some((role: string) => {
        return super_admin === role || theRoleArray.includes(role)
      })

      if (!hasRole) {
        el.parentNode && el.parentNode.removeChild(el)
      }
    }
    else {
      throw new Error(t('message:setRoleTag'))
    }
  },
}
