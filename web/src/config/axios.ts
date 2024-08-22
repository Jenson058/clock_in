// @ts-nocheck

import {getBaseUrl} from "@/config/request";
import axios, {AxiosError} from "axios";
import {ElMessage} from "element-plus";

/**
 * 通用配置
 */

let showErrorMessageDetail = import.meta.env.VITE_SHOW_ERROR_MESSAGE_DETAIL === "enable"

export const axiosRequest = axios.create({
    baseURL:import.meta.env.VITE_BACKEND_SERVER_URL + "/api",
    timeout:30000
})

/**
 * 请求拦截器
 */

axiosRequest.interceptors.request.use(
    function (config){
        // 装载token
        config.headers.token = localStorage.getItem('token')
        return config
    },

    function (err){
    }
)

/**
 * 响应拦截器
 */
axiosRequest.interceptors.response.use(
    function (response) {
        let result = response.data
        if (result.code == 501){
            ElMessage.error(result.msg)
            throw new Error(result.msg)
        }else if (result.code !== 200){
            throw new Error(result.msg ?? "错误")
        }
        return result;
    }
)