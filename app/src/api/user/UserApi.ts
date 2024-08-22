// @ts-nocheck
import SuperApi from "@/common/basic/SuperApi";
import {UserBo, UserSearchBo, UserVo} from "@/common/dto/user/User";
import {HttpMapping, useRequest} from "@/config/request";

@HttpMapping("/user")
class UserApi extends SuperApi<UserVo, UserBo, UserSearchBo>{

}

export default useRequest<UserApi>(UserApi)