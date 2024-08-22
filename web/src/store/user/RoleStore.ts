// @ts-nocheck

import {defineStore} from "pinia";
import {RoleVo} from "@/common/dto/user/Role";

const useRoleStore = defineStore(
    "RoleStore",
    {
        state:()=>{
            return {
                roleVo:new RoleVo()
            }
        },
        actions:{
            getRoleVo(){
                return this.roleVo
            },
            setRoleVo(value:RoleVo){
                this.roleVo = value
            }
            ,setRoleAuthAll(value:Array<string>){
                this.roleVo.authAll = value
            }
        }
    }
)

export default useRoleStore;