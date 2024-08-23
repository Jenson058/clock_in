import SuperApi from "@/common/basic/SuperApi";
import {RenewalBo, RenewalSearchBo, RenewalVo} from "@/common/dto/renewal/renewal";
import {axiosRequest} from "@/config/axios";
import type Result from "@/common/basic/Result";

class RenewalApi extends SuperApi<RenewalVo, RenewalBo, RenewalSearchBo> {
    constructor() {
        super("renewal");
    }

    async getNewVersion(){
        return await axiosRequest.post<object,Result<RenewalVo>>(`/renewal/version`);
    }
}

export default new RenewalApi()