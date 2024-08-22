import {Decoration} from "@/common/decoration/Decoration";

export class Column {
    public label?: string;
    public prop?: string;
    public width?: number;

    constructor(
        label?: string,
        prop?: string,
        width?: number,
    ) {
        this.label = label;
        this.prop = prop;
        this.width = width;
    }
}

export class ColumnUtil {

    private static readonly COLUMN = "Column"

    public static label(label: string) {
        return Decoration.propertyAnnotation(this.COLUMN, this.label.name, label)
    }

    public static prop(props?: string) {
        return Decoration.propertyAnnotation(this.COLUMN, this.prop.name, props)
    }

    public static width(width?: number) {
        return Decoration.propertyAnnotation(this.COLUMN, this.width.name, width)
    }

    public static getColumnList(clazz: Function) {
        let result = [] as Array<Column>
        let metadataCenter = Reflect.getMetadata(Decoration.MetadataCenter, clazz)
        if (!metadataCenter) return result
        let proper = metadataCenter.propertyMetadataManager
        let propSet = metadataCenter.propSet.pSet
        for (let param of propSet){
            let column = new Column()
            for (let prop of propSet.get(param[0])){
                let key = this.COLUMN +"::" + prop
                let value = proper.get(key)
                column[value.metadataKey] = value.metadataValue
            }
            column.prop = param[0]
            result.push(column)
        }
        return result
    }

}