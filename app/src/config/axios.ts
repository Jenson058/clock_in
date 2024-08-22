// @ts-nocheck
import axios from "axios";
import axiosAdapterUniapp from 'axios-adapter-uniapp'

export const axiosRequest = axios.create({
    baseURL:import.meta.env.VITE_BACKEND_SERVER_URL + "/api",
    timeout: 30000,
    adapter: axiosAdapterUniapp
})

/**
 * 请求拦截器
 */
axiosRequest.interceptors.request.use(
    function (config){
        config.headers.token = uni.getStorageSync("token")
        console.log(config)
        return config
    },function (error){
        console.log("请求错误",error)
    }
)


/**
 * 响应拦截器
 */
axiosRequest.interceptors.response.use(
    function (response){
        console.log(response)
        if (response.status == 200){
            console.log(response.data)
            return response.data
        }
    },function (error){
        console.log("响应错误",error)
    }
)