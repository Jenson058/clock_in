// @ts-nocheck

interface ImportMeta {
    readonly env: ImportMetaEnv
}

interface ImportMetaEnv {

    /**
     * 后端服务地址
     */
    readonly VITE_BACKEND_SERVER_URL: string,


    /**
     * 是否展示详细的错误信息
     */
    readonly VITE_SHOW_ERROR_MESSAGE_DETAIL: string
    /**
     * 是否启用 mock 数据服务
     */
    readonly VITE_MOCK_ENABLE: string,
}