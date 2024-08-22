import AdminLayout from "@/layout/admin/index.vue";
import AdminHomeRouter from "@/config/router/admin/home/index"
import {getI18n, i18n} from "@/config/i18n";
import UserRouter from "@/config/router/admin/user/UserRouter";
import ClockRouter from "@/config/router/admin/clock/index";
import RenewalRouter from "@/config/router/admin/renewal/index"


const { t } = i18n.global;
export default [
    {
        path:"/admin",
        name:"admin",
        meta:{
            title:getI18n("router.admin"),
        },
        component:AdminLayout,
        children:[
            ...AdminHomeRouter,
            ...UserRouter,
            ...ClockRouter,
            ...RenewalRouter
        ]
    }
]