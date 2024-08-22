// @ts-nocheck
import {getI18n} from "@/config/i18n";
import renewal from "@/assets/icon/更新.svg"

export default [
    {
        path:"/renewal",
        name:"renewal",
        meta:{
            title:"更新",
            icon:renewal,
        },
        children:[
            {
                path:"/version_list",
                name:"version_list",
                meta:{
                    title:"版本列表",
                },
                component:() => import("@/view/admin/renewal/index.vue")
            }
        ]
    }
]