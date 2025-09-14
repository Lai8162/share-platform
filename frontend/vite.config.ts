import { resolve } from 'node:path'
import process from 'node:process'
import vue from '@vitejs/plugin-vue'
import { defineConfig, loadEnv } from 'vite'
import { createSvgIconsPlugin } from 'vite-plugin-svg-icons'
import WindiCSS from 'vite-plugin-windicss'

export default defineConfig(({ mode }) => {
  const env = loadEnv(mode, process.cwd(), '')
  return {
    define: {
      env,
    },
    plugins: [
      vue(),
      WindiCSS(),
      createSvgIconsPlugin({
        iconDirs: [resolve(resolve(__dirname, 'src'), 'assets/icons/svgs')],
        symbolId: 'icon-[dir]-[name]',
      }),
    ],
    resolve: {
      alias: {
        '@': resolve(__dirname, './src'),
      },
    },
    css: {
      preprocessorOptions: {
        scss: {
          api: 'modern-compiler',
          additionalData: `@use '@/assets/css/global.scss' as *; @use '@/assets/css/element.scss' as *;`,
        },
      },
    },
    server: {
      port: 7001,
    },
  }
})
