// @ts-nocheck
import type {SuperSearchBo} from "@/common/basic/SuperSearchBo";
import {GetMapping, PostMapping, RequestBody, RequestParam} from "@/config/request";
import type Result from "@/common/basic/Result";
import type PageVo from "@/common/basic/PageVo";

export default class SuperApi<Vo,Bo,SearchBo extends SuperSearchBo>{

    @PostMapping("/page_all")
    pageAll(@RequestBody() searchBo:SearchBo):Promise<Result<PageVo<Vo>> | undefined>{
        return null;
    }

    @PostMapping("/list_all")
    listAll(@RequestBody() searchBo:SearchBo):Promise<Result<Array<Vo>> | undefined>{
        return null;
    }

    @GetMapping("/get")
    get(@RequestParam() obj:object):Promise<Result<Vo> | undefined>{
        return null;
    }

    @PostMapping("/edit")
    edit(@RequestBody() bo:Bo):Promise<Result<Vo> | undefined>{
        return null;
    }

    @PostMapping("/edit_all")
    editAll(@RequestBody() boList:Array<Bo>):Promise<Result<Array<Vo>> | undefined>{
        return null;
    }

    @GetMapping("/del")
    del(@RequestParam obj:object):Promise<Result<Array<Vo>> | undefined>{
        return null;
    }
    @PostMapping("/del")
    delAll(@RequestBody() searchBo:SearchBo):Promise<Result<Array<Vo>> | undefined>{
        return null;
    }




}