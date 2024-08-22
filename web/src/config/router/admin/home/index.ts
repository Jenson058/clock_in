// @ts-nocheck
import home from "@/assets/icon/主页.svg"
import {getI18n, i18n} from "@/config/i18n";

export default [
    {
        path:"home",
        name:"home",
        icon: home,
        meta:{
            title:getI18n("router.home")
        },
        children:[
            {
                path:"home_index",
                name:"home_index",
                meta:{
                    title:getI18n("router.home_index")
                },
                component:() => import("@/view/admin/home/index.vue")
            },
        ]
    }
]