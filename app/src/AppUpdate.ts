// @ts-nocheck
import RenewalApi from "@/api/renewal/RenewalApi";
import {FileVo} from "@/common/dto/file/File";
import useSystemStore from "@/store/system/SystemStore";

export default function AppUpdate(){
    RenewalApi.getNewVersion()
        .then(function (result) {
            let version = import.meta.env.VITE_VERSION
            if (result.data){
                const systemStore = useSystemStore()
                systemStore.setVersion(version)
                systemStore.setNowNewVersion(result.data.version)
                if (version !== result.data.version){
                    showModal(result.data.fileId)
                }
            }
    })
}


function showModal(id:number){
    uni.showModal({
        title: '紧急升级通知',
        content: '亲爱的用户，为了提供更好的使用体验与功能改进，我们进行了系统升级。请立即完成强制升级，否则将无法正常使用APP。感谢您的配合与支持！',
        confirmText: '立即更新',
        cancelText: "取消", // 取消按钮的文字
        showCancel: true, // 是否显示取消按钮，默认为 true
        confirmColor: '#39B54A',
        cancelColor: '#f55850',
        success:function (res){
            if (res.confirm){
                plus.runtime.openURL(FileVo.getUploadUrl(id))
            }else {

            }
        }
    })
}