// @ts-nocheck
import {defineStore} from "pinia";
import {UserVo} from "@/common/dto/user/User";
import {RenewalVo} from "@/common/dto/renewal/renewal";
import RenewalApi from "@/api/renewal/RenewalApi";

const useSystemStore = defineStore("systemStore", {
    state: () => {
        return {
            userVo: new UserVo(),
            renewalVo: new RenewalVo(),
            versionId: 1
        }
    },
    actions: {
        setUserVo(value: UserVo) {
            this.userVo = value;
        },
        getUserVo() {
            if (this.userVo.id) {
                return this.userVo
            } else if (uni.getStorageSync("userVo")) {
                this.userVo = uni.getStorageSync("userVo")
                return this.userVo
            }
            return new UserVo()
        },
        setRenewalVo(value: RenewalVo) {
            uni.setStorageSync("renewalVo", value)
            this.renewalVo = value
        },
        getRenewalVo() {
            if (this.renewalVo.id) {
                return this.renewalVo
            } else if (uni.getStorageSync("renewalVo")) {
                this.renewalVo = uni.getStorageSync("renewalVo")
                return this.renewalVo
            }
            return new RenewalVo()
        },
        getVersion(){
            RenewalApi.get({id:this.versionId})
                .then(function (result){
                    this.renewalVo = result?.content
                })
        }
    }
})

export default useSystemStore;