// @ts-nocheck
import AdminRouter from "@/config/router/admin/index.ts"
import LoginRouter from "@/config/router/login/index.ts"
import {createRouter, createWebHistory} from "vue-router";
import useUserStore from "@/store/user/UserStore";
import {ElMessage} from "element-plus";
import useSystemStore from "@/store/system/SystemStore";
import UserApi from "@/api/user/UserApi";
import {ListUtil} from "@/util/ListUtil";

const routes = [
    ...LoginRouter,
    ...AdminRouter,
]

const router = createRouter({
    history: createWebHistory(),
    routes:routes
})

router.beforeEach((to, from, next)=>{
    document.title = to.meta.title as string
    router.options.routes = routes
    if (to.name == "login"){
        next()
        return
    }
    let userStore = useUserStore()
    if (!userStore.getUserVo().id){
        let id = localStorage.getItem("userId") as number
        const token: string | null = localStorage.getItem("token");
        if (!id || !token){
            ElMessage.warning("未登录不能访问")
            next("/login")
            return;
        }

        UserApi.get({id:id})
            .then((result) => {
                userStore.setUserVo(result?.content)
                if (userStore.getUserVo().code !== "admin" && to.meta.auth && !ListUtil.exist(result?.content?.authList,to.meta.auth)){
                    ElMessage.warning("没有权限访问"+to.meta.title)
                    next("/login")
                    return
                }
                const systemStore = useSystemStore();
                systemStore.setRouterList(to.matched)
                next()
                return;
            })
    }else {
        if (userStore.getUserVo().code !== "admin" && to.meta.auth && !ListUtil.exist(userStore.getUserVo().authList,to.meta.auth)){
            ElMessage.warning("没有权限访问"+to.meta.title)
            next("/login")
            return
        }
        const systemStore = useSystemStore();
        systemStore.setRouterList(to.matched)
        next()
        return;
    }
})

export default router