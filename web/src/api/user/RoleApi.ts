import {HttpMapping, useRequest} from "@/config/request";
import SuperApi from "@/common/basic/SuperApi";
import {RoleBo, RoleSearchBo, RoleVo} from "@/common/dto/user/Role";

@HttpMapping("/role")
class RoleApi extends SuperApi<RoleVo, RoleBo, RoleSearchBo>{

}

export default useRequest<RoleApi>(RoleApi)