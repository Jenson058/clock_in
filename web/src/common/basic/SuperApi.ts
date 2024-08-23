// @ts-nocheck

import {SuperSearchBo} from "@/common/basic/SuperSearchBo";
import Result from "@/common/basic/Result";
import {LoginVo} from "@/common/dto/system/login";
import PageVo from "@/common/basic/PageVo";
import {GetMapping, PostMapping, RequestBody, RequestParam} from "@/config/request";
import {axiosRequest} from "@/config/axios";
import {ObjectUtil} from "@/util/ObjectUtil";

export default class SuperApi<Vo, Bo, SearchBo extends SuperSearchBo> {

    public readonly _url: string;

    constructor(_url) {
        this._url = _url;
    }

    async pageAll(searchBo: SearchBo) {
        return await axiosRequest.post<SearchBo, Result<PageVo<Vo>>>(`/${this._url}/page_all`, searchBo);
    }

    async listAll(searchBo: SearchBo) {
        return await axiosRequest.post<SearchBo, Result<Array<Vo>>>(`/${this._url}/list_all`, searchBo,);
    }

    async get(obj: object) {
        return await axiosRequest.get<object, Result<Vo>>(`/${this._url}/get?${ObjectUtil.toGetParams(obj)}`);
    }

    async edit(bo: Bo) {
        return await axiosRequest.post<Bo, Result<Vo>>(`/${this._url}/edit`, bo);
    }

    async editAll(boList: Array<Bo>) {
        return await axiosRequest.post<Array<Bo>, Result<Array<Vo>>>(`/${this._url}/edit_all`, boList);
    }

    async del(obj: object) {
        return await axiosRequest.get<object, Result<Vo>>(`/${this._url}/del${ObjectUtil.toGetParams(obj)}`);
    }

    async delAll(searchBo: SearchBo) {
        return await axiosRequest.post<SearchBo, Result<Array<Vo>>>(`/${this._url}/del`, searchBo);
    }


}