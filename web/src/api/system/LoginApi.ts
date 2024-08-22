// @ts-nocheck
import {HttpMapping, PostMapping, RequestBody, useRequest} from "@/config/request";
import {LoginBo, LoginVo} from "@/common/dto/system/login";
import Result from "@/common/basic/Result";

@HttpMapping("/system")
class LoginApi{

    @PostMapping("/login")
    async login(@RequestBody() loginBo:LoginBo):Promise<Result<LoginVo> | undefined>{
        return undefined;
    }

    @PostMapping("/update_password")
    async updatePassword(@RequestBody() loginBo:LoginBo):Promise<Result<LoginVo> | undefined>{
        return undefined;
    }

}

export default useRequest<LoginApi>(LoginApi)