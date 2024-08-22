export class ObjectUtil{
    public static isEmpty(obj:any){
        return isEmpty(obj)
    }

    public static isEqual(f:any,s:any){
        if (typeof f == "undefined"){
            return (typeof s == "undefined");
        }

        if (typeof f !== typeof s)
            return false

        if (Object.keys(f).length != Object.keys(s).length)
            return false

        for (const k in f){
            if (typeof f[k] == "object" || s[k] == "object"){
                if (!this.isEqual(f[k],s[k])) return false
            }else if (f[k] !== s[k]) return  false
        }
        return true
    }

    public static toGetParams(obj:object){
        let list = [] as Array<string>
        for (const k in obj){
            list.push(k+"="+obj[k])
        }
        return list.join("&")
    }
}

function isEmpty(obj:any){
    return obj == undefined
}
