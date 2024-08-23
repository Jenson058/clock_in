// @ts-nocheck
import {HttpMapping, PostMapping, RequestBody, useRequest} from "@/config/request";
import {LoginBo, LoginVo} from "@/common/dto/system/login";
import Result from "@/common/basic/Result";
import {axiosRequest} from "@/config/axios";

class LoginApi{

    async login(@RequestBody() loginBo:LoginBo){
        return await axiosRequest.post<LoginBo,Result<LoginVo>>(`/system/login`,loginBo);
    }

    async updatePassword(@RequestBody() loginBo:LoginBo){
        return await axiosRequest.post<LoginBo,Result<LoginVo>>(`/system/update_password`,loginBo);
    }

}

export default new LoginApi()