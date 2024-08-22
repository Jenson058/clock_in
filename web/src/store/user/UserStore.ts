// @ts-nocheck

import {defineStore} from "pinia";
import SuperStore from "@/common/basic/SuperStore";
import {UserVo} from "@/common/dto/user/User";
import {state} from "vue-tsc/out/shared";

const useUserStore = defineStore(
    "UserStore",
    {
        state:()=>{
            return{
                userVo:new UserVo(),
            }
        },
        actions:{
            setUserVo(value?:UserVo){
                this.userVo = value ?? new UserVo()
            },
            getUserVo(){
                return this.userVo
            }
        }
    }
)

export default useUserStore;