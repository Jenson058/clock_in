import {SuperSearchBo} from "@/common/basic/SuperSearchBo";

export class UserVo{
    public id?:number
    public code?:string
    public name?:string
    public nickname?:string
    public birthday?:Date
    public age?:number
    public sex?:string
    public avatar?:string
    public phone?:string
    public e_mail?:string
    public roleList?:Array<number>
    public authList?:Array<string>


}

export class UserBo{
    public id?:number
    public code?:string
    public name?:string
    public nickname?:string
    public sex?:string
    public avatar?:string
    public phone?:string
    public e_mail?:string
    public roleList?:Array<number>
}

export class UserSearchBo extends SuperSearchBo{

}