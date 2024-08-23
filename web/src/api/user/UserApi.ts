// @ts-nocheck
import SuperApi from "@/common/basic/SuperApi";
import {UserBo, UserSearchBo, UserVo} from "@/common/dto/user/User";
import {HttpMapping, useRequest} from "@/config/request";

class UserApi extends SuperApi<UserVo, UserBo, UserSearchBo>{

    constructor() {
        super("user");
    }
}

export default new UserApi()