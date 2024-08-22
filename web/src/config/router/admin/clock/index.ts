// @ts-nocheck
import home from "@/assets/icon/人员.svg";
import {getI18n} from "@/config/i18n";

export default [
    {
        path:"clock",
        name:"clock",
        icon: home,
        meta:{
            title:"打卡"
        },
        children:[
            {
                path:"log_list",
                name:"log_list",
                meta:{
                    title:"打卡记录",
                },
                component:() => import("@/view/admin/clock/index.vue")
            },
            {
                path:"look_log",
                name:"look_log",
                meta:{
                    title:"查看打卡",
                },
                component:() => import("@/view/admin/clock/LookLog.vue")
            }
        ]
    }
]