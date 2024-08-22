
import {ColumnUtil} from "@/common/decoration/Column";
import {SuperSearchBo} from "@/common/basic/SuperSearchBo";

export class UserVo{
    public id?:number
    @ColumnUtil.label("账号")
    @ColumnUtil.prop()
    public code?:string
    @ColumnUtil.label("姓名")
    @ColumnUtil.prop()
    public name?:string
    @ColumnUtil.label("昵称")
    @ColumnUtil.prop()
    public nickname?:string
    @ColumnUtil.label("出生日期")
    @ColumnUtil.prop()
    public birthday?:Date
    @ColumnUtil.label("年龄")
    @ColumnUtil.prop()
    public age?:number
    @ColumnUtil.label("性别")
    @ColumnUtil.prop()
    public sex?:string
    public avatar?:string
    @ColumnUtil.label("手机号")
    @ColumnUtil.prop()
    public phone?:string
    @ColumnUtil.label("邮箱")
    @ColumnUtil.prop()
    public e_mail?:string
    public roleList?:Array<number> = []
    public authList?:Array<string> = []


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