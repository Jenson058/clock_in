import {SuperSearchBo} from "@/common/basic/SuperSearchBo";
import {UploadFile} from "element-plus";

export class FileVo{
    public id?:number
    public oldFileName?:string
    public mdKey?:string
    public type?:string
    public suffix?:string
    public number_?:number
    public count?:number
    public finish?:boolean

    public static toUploadFile(fileVo: FileVo): UploadFile {
        return {
            name: fileVo?.oldFileName ?? "未命名文件",
            url: `${import.meta.env.VITE_BACKEND_SERVER_URL}/api/file/download?id=${fileVo?.id}`,
            response: fileVo.id
        } as UploadFile;
    }

    public static getUploadUrl(fileId : string): string {
        return `${import.meta.env.VITE_BACKEND_SERVER_URL}/api/file/download?id=${fileId}`
    }
}

export class FileBo{
    public oldFileName?:string
    public mdKey?:string
    public type?:string
    public suffix?:string
    public number_?:number
    public count?:number


    constructor(
        oldFileName?: string,
        mdKey?: string,
        type?: string,
        suffix?: string,
        number_?: number,
        count?: number
    ) {
        this.oldFileName = oldFileName;
        this.mdKey = mdKey;
        this.type = type;
        this.suffix = suffix;
        this.number_ = number_;
        this.count = count;
    }
}

export class FileSearchBo extends SuperSearchBo{
}