import {SuperSearchBo} from "@/common/basic/SuperSearchBo";
import {ColumnUtil} from "@/common/decoration/Column";

export class RenewalVo {
    public id?:number
    public fileId ?:string;
    @ColumnUtil.label("版本")
    @ColumnUtil.prop()
    public version ?:string;
    @ColumnUtil.label("修改日期")
    @ColumnUtil.prop()
    public updatedAt?:Date
}

export class RenewalBo {
    public id?:number
    public fileId ?:string;
    public version ?:string;
}

export class RenewalSearchBo extends SuperSearchBo{

}
