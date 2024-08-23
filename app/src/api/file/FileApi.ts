// @ts-nocheck
import SuperApi from "@/common/basic/SuperApi";
import {FileBo, FileSearchBo, FileVo} from "@/common/dto/file/File"
import Result from "@/common/basic/Result";
import {axiosRequest} from "@/config/axios";
import {ListUtil} from "@/util/ListUtil";
import {ObjectUtil} from "@/util/ObjectUtil";


class FileApi extends SuperApi<FileVo, FileBo, FileSearchBo> {

    constructor() {
        super("file");
    }

    async upload(fromData: FormData) {
        return await axiosRequest.post<FormData, Result<FileVo>>(`/file/upload`, fromData)
    }


    async download(obj: object) {
        return await axiosRequest.get<object, any>(`/file/download? ${ObjectUtil.toGetParams(obj)}`)
    }
}

export default new FileApi()