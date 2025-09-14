/// <reference types="vite/client" />

interface ImportMetaEnv {
  VITE_APP_TITLE: string
  // 其他环境变量声明...
}

interface ImportMeta {
  readonly env: ImportMetaEnv
}
