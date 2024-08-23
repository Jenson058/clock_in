import RenewalApi from "@/api/renewal/RenewalApi";

export default function AppUpdate(){
    RenewalApi.getNewVersion()
        .then(function (result) {
            uni.showModal({
                title: '紧急升级通知',
                content: '亲爱的用户，为了提供更好的使用体验与功能改进，我们进行了系统升级。请立即完成强制升级，否则将无法正常使用APP。感谢您的配合与支持！',
                confirmText: '立即更新',
                showCancel: false,
                success:function (){
                    console.log(111)
                }
            })
    })
}