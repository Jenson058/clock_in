import {HttpMapping, useRequest} from "@/config/request";
import SuperApi from "@/common/basic/SuperApi";
import {RoleBo, RoleSearchBo, RoleVo} from "@/common/dto/user/Role";

class RoleApi extends SuperApi<RoleVo, RoleBo, RoleSearchBo> {
    constructor() {
        super("role");
    }


}

export default new RoleApi()