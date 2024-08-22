import {createApp} from 'vue'
import '@/style.css'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from '@/App.vue'
import router from "@/config/router"
import {i18n} from "@/config/i18n"
import 'reflect-metadata';
import zhLocale from 'element-plus/es/locale/lang/zh-cn'
import '@imengyu/vue3-context-menu/lib/vue3-context-menu.css'
import ContextMenu from '@imengyu/vue3-context-menu'
import pinia from "@/config/pinia";
import useUserStore from "@/store/user/UserStore";
import {ListUtil} from "@/util/ListUtil";

const app = createApp(App)

app.use(ElementPlus,{locale:zhLocale})
    .use(router).use(ContextMenu).use(i18n).use(pinia)


app.directive("auth",(el,binding) =>{
    let useStore = useUserStore()
    let auth = binding.value
    if (typeof auth !== "undefined" &&!ListUtil.exist(useStore.getUserVo().authList,auth) && useStore.getUserVo().code !== "admin"){
        el.style.display = "none"
    }
})

app.mount('#app')
