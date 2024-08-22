export class ListUtil{

    public static isEmpty(list:Array<any>){
        return isEmpty(list)
    }

    public static exist(list: Array<any> | undefined,obj:any){
        return exist(list,obj)
    }

    public static stringToNumberList(str: string) {

        return str?.split(',')
            .map((id) => Number(id))
            .map(idString => Number(idString))
            .filter(idNumber => Number.isInteger(idNumber)) ?? []
    }

    public static listToString(list: Array<number | undefined>) {
        return list.map(idNumber => String(idNumber)).join(',')
    }

    public static stringListToString(list: Array<string | undefined>) {
        return list.join(',')
    }

    public static stringToStringList(str: string) {
        return str?.split(',')
    }
}

function isEmpty(list:Array<any>){
    return typeof list == "undefined" || list.length == 0
}

function exist(list: Array<any> | undefined,obj:any){
    if (list == undefined || list.length == 0 || obj == undefined)
        return false
    return list.filter(it => typeof obj == typeof it && obj == it).length > 0
}