// @ts-nocheck
import SuperApi from "@/common/basic/SuperApi";
import {FileBo, FileSearchBo, FileVo} from "@/common/dto/file/File";
import {GetMapping, HttpMapping, PostMapping, RequestBody, RequestParam, useRequest} from "@/config/request";
import Result from "@/common/basic/Result";

@HttpMapping("/file")
class FileApi extends SuperApi<FileVo, FileBo, FileSearchBo>{

    @PostMapping("/upload")
    async upload(@RequestBody() fromData:FormData):Promise<Result<FileVo> | undefined>{}

    @GetMapping("/download")
    async download(@RequestParam() obj:object):Promise<any>{}
}

export default useRequest<FileApi>(FileApi)