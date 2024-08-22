import {SuperSearchBo} from "@/common/basic/SuperSearchBo";
import {ColumnUtil} from "@/common/decoration/Column";

export class RoleVo{
    public id?:number
    @ColumnUtil.label("名称")
    @ColumnUtil.prop()
    public name?:string
    public authAll?:Array<string>
}

export class RoleBo{
    public id?:number
    public name?:string
    public authAll?:Array<string>
}

export class RoleSearchBo extends SuperSearchBo{
}