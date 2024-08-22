import type {UserVo} from "@/common/dto/user/User";


export class LoginVo{
    public userVo?:UserVo;
    public token?:string;
}

export class LoginBo{
    public code ?:string
    public password?:string
    public oldPassword?:string
    public subPassword?:string
}