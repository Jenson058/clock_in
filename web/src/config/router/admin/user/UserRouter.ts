// @ts-nocheck

import home from "@/assets/icon/人员.svg";
import {getI18n, i18n} from "@/config/i18n";
const { t } = i18n.global;
export default [
    {
        path:"user",
        name:"user",
        icon: home,
        meta:{
            title:getI18n("router.user")
        },
        children:[
            {
                path:"user_list",
                name:"user_list",
                meta:{
                    title:getI18n("router.user_list"),
                    auth:"user:retrieve"
                },
                component:() => import("@/view/admin/user/user/index.vue")
            },
            {
                path:"auth",
                name:"auth",
                meta:{
                    title:getI18n("router.auth"),
                    isShow:false,
                    auth: "auth:retrieve"
                },
                component:() => import("@/view/admin/user/auth/index.vue")
            },
            {
                path:"role",
                name:"role",
                meta:{
                    title:getI18n("router.role"),
                    auth: "role:retrieve"
                },
                component:() => import("@/view/admin/user/role/index.vue")
            },
        ]
    }
]