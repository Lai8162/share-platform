import { t } from '@/locales/i18n'
import useUserStore from '@/stores/user'
import { getToken } from '@/utils/auth'
import cache from '@/utils/cache'
import errorCode from '@/utils/code'
import axios from 'axios'
import qs from 'qs'
import { isBlob } from '@/utils/validate'
import { saveAs } from 'file-saver'

let downloadLoadingInstance: any

export const isRelogin = { show: false }

axios.defaults.withCredentials = true
axios.defaults.headers['Content-Type'] = 'application/json;charset=utf-8'
const service = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_API,
  timeout: 10000,
})

// request拦截器
service.interceptors.request.use(
  (config) => {
    // 是否需要设置 token
    const isToken = (config.headers || {}).isToken === false
    // 让每个请求携带自定义token 请根据实际情况自行修改
    if (getToken() && !isToken) {
      config.headers.Authorization = `Bearer ${getToken()}`
    }
    // 是否需要防止数据重复提交
    const isRepeatSubmit = (config.headers || {}).repeatSubmit === false
    if (!isRepeatSubmit && (config.method === 'post' || config.method === 'put')) {
      const requestObj = {
        url: config.url,
        data: typeof config.data === 'object' ? JSON.stringify(config.data) : config.data,
        time: new Date().getTime(),
      }
      const requestSize = Object.keys(JSON.stringify(requestObj)).length // 请求数据大小
      const limitSize = 5 * 1024 * 1024 // 限制存放数据5M
      if (requestSize >= limitSize) {
        console.warn(`[${config.url}]: ${t('message:requestLimit')}`)
        ElMessage.warning(`[${config.url}]: ${t('message:requestLimit')}`)
        return config
      }
      const sessionObj = cache.session.getJSON('sessionObj')
      if (sessionObj === undefined || sessionObj === null || sessionObj === '') {
        cache.session.setJSON('sessionObj', requestObj)
      } else {
        const s_url = sessionObj.url // 请求地址
        const s_data = sessionObj.data // 请求数据
        const s_time = sessionObj.time // 请求时间
        const interval = 1000 // 间隔时间(ms)，小于此时间视为重复提交
        if (s_data === requestObj.data && requestObj.time - s_time < interval && s_url === requestObj.url) {
          console.warn(`[${s_url}]: ${t('message:repeatSumbit')}`)
          ElMessage.warning(`[${s_url}]: ${t('message:repeatSumbit')}`)
          return Promise.reject(new Error(t('message:repeatSumbit')))
        } else {
          cache.session.setJSON('sessionObj', requestObj)
        }
      }
    }
    return config
  },
  (error) => {
    Promise.reject(error)
  },
)

// 响应拦截器
service.interceptors.response.use(
  (res) => {
    // 未设置状态码则默认成功状态
    const code = res.data.code || 200
    // 获取错误信息
    const msg = errorCode[code] || res.data.msg || errorCode.default
    // 二进制数据则直接返回
    if (res.request.responseType === 'blob' || res.request.responseType === 'arraybuffer') {
      return res.data
    }
    if (code === 401) {
      if (!isRelogin.show) {
        isRelogin.show = true
        ElMessageBox.confirm(t('message:reLoginMsg'), t('message:systemNote'), { confirmButtonText: t('button:reLogin'), cancelButtonText: t('button:cancel'), type: 'warning' })
          .then(() => {
            isRelogin.show = false
            useUserStore()
              .logOut()
              .then(() => {
                location.href = '/index'
              })
          })
          .catch(() => {
            isRelogin.show = false
          })
      }
      return Promise.reject(t('message:reLoginMsg'))
    } else if (code === 500) {
      ElMessage({ message: msg, type: 'error' })
      return Promise.reject(new Error(msg))
    } else if (code === 601) {
      ElMessage({ message: msg, type: 'warning' })
      return Promise.reject(new Error(msg))
    } else if (code !== 200) {
      ElNotification.error({ title: msg })
      return Promise.reject(new Error(msg))
    } else {
      return Promise.resolve(res.data)
    }
  },
  (error) => {
    let { message } = error
    if (message === 'Network Error') {
      message = t('message:portError')
    } else if (message.includes('timeout')) {
      message = t('message:timeout')
    } else if (message.includes('Request failed with status code')) {
      message = `${t('message:systemInterface')} ${message.substr(message.length - 3)} ${t('message:exception')}`
    }
    ElMessage.error({ message, type: 'error', duration: 5 * 1000 })
    return Promise.reject(error)
  },
)

const request = {
  /**
   * get方法，对应get请求
   * @param {String} url [请求的url地址]
   * @param {Object} params [请求时携带的参数]
   */
  get(url: string, params: any) {
    return new Promise((resolve, reject) => {
      service
        .get(url, { params: params })
        .then((res) => {
          resolve(res.data)
        })
        .catch((err) => {
          reject(err.data)
        })
    })
  },

  /**
   * post方法，对应post请求
   * @param {String} url [请求的url地址]
   * @param {Object} params [请求时携带的参数]
   */
  post(url: string, params: any, isForm: boolean = false) {
    return new Promise((resolve, reject) => {
      service
        .post(url, params, !isForm ? {} : { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } })
        .then((res) => {
          resolve(res.data)
        })
        .catch((err) => {
          reject(err.data)
        })
    })
  },

  /**
   * put方法，对应put请求
   * @param {String} url [请求的url地址]
   * @param {Object} params [请求时携带的参数]
   */
  put(url: string, params: any, isForm: boolean = false) {
    return new Promise((resolve, reject) => {
      service
        .post(url, params, !isForm ? {} : { headers: { 'Content-Type': 'application/x-www-form-urlencoded' } })
        .then((res) => {
          resolve(res.data)
        })
        .catch((err) => {
          reject(err.data)
        })
    })
  },

  /**
   * delete方法，对应delete请求
   * @param {String} url [请求的url地址]
   * @param {Object} params [请求时携带的参数]
   */
  delete(url: string, params: any) {
    return new Promise((resolve, reject) => {
      service
        .delete(url, { params: params })
        .then((res) => {
          resolve(res.data)
        })
        .catch((err) => {
          reject(err.data)
        })
    })
  },

  /**
   * 通用下载方法
   * @param {String} url [请求的url地址]
   * @param {Record} params [请求时携带的参数]
   * @param {string} fileName [文件名称]
   * @param {Record} config [头部其他的请求参数]
   */
  async download(url: string, params: Record<string, any>, fileName: string, config: Record<string, any>) {
    downloadLoadingInstance = ElLoading.service({ text: t('message:downloadMsg'), background: 'rgba(0, 0, 0, 0.7)' })
    return service
      .post(url, params, {
        transformRequest: [
          (params) => {
            return qs.stringify(params)
          },
        ],
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        responseType: 'blob',
        ...config,
      })
      .then(async (data: any) => {
        if (isBlob(data)) {
          const blob = new Blob([data])
          saveAs(blob, fileName)
        } else {
          const resText = await data.text()
          const rspObj = JSON.parse(resText)
          const errMsg = errorCode[rspObj.code] || rspObj.msg || errorCode.default
          ElMessage.error(errMsg)
        }
        downloadLoadingInstance.close()
      })
      .catch((r) => {
        console.error(r)
        ElMessage.error(t('message:downloadError'))
        downloadLoadingInstance.close()
      })
  },
}

export default request
