// @ts-nocheck
import {GetMapping, HttpMapping, RequestParam, useRequest} from "@/config/request";
import Result from "@/common/basic/Result";
import {KeyValue} from "@/common/basic/KeyValue";

@HttpMapping("/auth")
class AuthApi{

    @GetMapping("/all")
    public async all() :Promise<Result<Array<KeyValue<string, Array<string>>>>>{
        return null;
    }

}

export default useRequest<AuthApi>(AuthApi)
