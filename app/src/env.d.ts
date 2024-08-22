interface ImportMeta{
    readonly  env:ImportMetaEnv
}

interface ImportMetaEnv{
    /**
     * 后端服务地址
     */
    readonly VITE_BACKEND_SERVER_URL: string,

}