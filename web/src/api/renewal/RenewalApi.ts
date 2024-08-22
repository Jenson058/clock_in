import SuperApi from "@/common/basic/SuperApi";
import {RenewalBo, RenewalSearchBo, RenewalVo} from "@/common/dto/renewal/renewal";
import {HttpMapping, useRequest} from "@/config/request";

@HttpMapping("/renewal")
class RenewalApi extends SuperApi<RenewalVo,RenewalBo,RenewalSearchBo>{

}

export default useRequest<RenewalApi>(RenewalApi)