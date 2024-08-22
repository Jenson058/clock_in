// @ts-nocheck
import {getI18n, i18n} from "@/config/i18n";

const { t } = i18n.global;
export default [
    {

        path:"/login",
        alias: "/",
        name:"login",
        meta:{
            title:getI18n("router.login"),
        },
        component:() => import("@/layout/login/index.vue"),
    }
]