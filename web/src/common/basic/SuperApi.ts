// @ts-nocheck

import {SuperSearchBo} from "@/common/basic/SuperSearchBo";
import Result from "@/common/basic/Result";
import {LoginVo} from "@/common/dto/system/login";
import PageVo from "@/common/basic/PageVo";
import {GetMapping, PostMapping, RequestBody, RequestParam} from "@/config/request";

export default class SuperApi<Vo, Bo, SearchBo extends SuperSearchBo> {

    @PostMapping("/page_all")
    async pageAll(@RequestBody() searchBo: SearchBo): Promise<Result<PageVo<Vo>> | undefined> {
        return undefined;
    }

    @PostMapping("/list_all")
    async listAll(@RequestBody() searchBo: SearchBo): Promise<Result<Array<Vo>> | undefined> {
        return undefined;
    }

    @GetMapping("/get")
    async get(@RequestParam obj: object): Promise<Result<Vo> | undefined> {
        return undefined;
    }

    @PostMapping("/edit")
    async edit(@RequestBody() bo: Bo): Promise<Result<Vo> | undefined> {
        return undefined;
    }

    @PostMapping("/edit_all")
    async editAll(@RequestBody() boList: Array<Bo>): Promise<Result<Array<Vo>> | undefined> {
        return undefined;
    }

    @GetMapping("/del")
    async del(@RequestParam obj: object): Promise<Result<Array<Vo>> | undefined> {
        return undefined;
    }

    @PostMapping("/del")
    async delAll(@RequestBody() searchBo: SearchBo): Promise<Result<Array<Vo>> | undefined> {
        return undefined;
    }


}