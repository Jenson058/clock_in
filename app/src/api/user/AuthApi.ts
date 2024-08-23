// @ts-nocheck
import {GetMapping, HttpMapping, RequestParam, useRequest} from "@/config/request";
import Result from "@/common/basic/Result";
import {KeyValue} from "@/common/basic/KeyValue";
import {axiosRequest} from "@/config/axios";

class AuthApi{

    public async all(){
        return await axiosRequest.get<object,Result<Array<KeyValue<string, Array<string>>>>>(`/auth/all`);
    }

}

export default new AuthApi()
