// @ts-nocheck
import {createI18n, useI18n} from "vue-i18n";
import {en_US} from "@/config/i18n/en_US";
import {zh_CN} from "@/config/i18n/zh_CN";
export const i18n = createI18n({
    legacy: false,
    locale: "zh_CN",    //默认中文
    messages:{
        zh_CN,
        en_US
    }
})

const {t,locale} = i18n.global
export function getI18n(key:string){
    return t(key)
}