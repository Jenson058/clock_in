import {defineStore} from "pinia";
import {useRouter} from "vue-router";

const useSystemStore = defineStore(
    "systemStore",
    {
        state:()=>{
            return{
                routerList:[],
                router:useRouter()
            }
        },
        actions:{
            getRouterList(){
                return this.routerList
            },
            setRouterList(value:[]){
                this.routerList = value
            }
        }
    }
)

export default useSystemStore;