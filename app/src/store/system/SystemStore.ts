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
            version: "1.0.0",
            nowNewVersion:"1.0.0"
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
        setVersion(value){
          this.version = value
        },
        getVersion(){
           return this.version;
        },
        setNowNewVersion(value){
            this.nowNewVersion = value;
        },
        getNowNewVersion(){
            return this.nowNewVersion;
        }
    }
})

export default useSystemStore;