import { SuperSearchBo } from "@/common/basic/SuperSearchBo";
import {ColumnUtil} from "@/common/decoration/Column";

export class LogVo {
    public id?:number
    public userId?:number
    @ColumnUtil.label("打卡人")
    @ColumnUtil.prop()
    public userName?:string
    @ColumnUtil.label("年")
    @ColumnUtil.prop()
    public year?:number
    @ColumnUtil.label("月")
    @ColumnUtil.prop()
    public mouth?:number
    @ColumnUtil.label("日")
    @ColumnUtil.prop()
    public day?:number
}

export class LogBo {
    public id?:number
    public userId?:number
}

export class LogSearchBo extends SuperSearchBo{
    public userId?:number

}