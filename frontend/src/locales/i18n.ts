import buttonEn from './en/button'
import messageEn from './en/message'
import buttonZh from './zh/button'
import messageZh from './zh/message'
import { createI18n } from 'vue-i18n'

const i18n = createI18n({
  locale: localStorage.getItem('language') || 'zh-Hant',
  globalInjection: true, // 全局注册，代表能在sfc中使用$t
  legacy: false,
  messages: {
    en: {
      button: buttonEn,
      message: messageEn,
    },
    zh: {
      button: buttonZh,
      message: messageZh,
    },
  },
})

export const { t } = i18n.global as any

export default i18n
