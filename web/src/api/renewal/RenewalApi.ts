import SuperApi from "@/common/basic/SuperApi";
import {RenewalBo, RenewalSearchBo, RenewalVo} from "@/common/dto/renewal/renewal";

class RenewalApi extends SuperApi<RenewalVo, RenewalBo, RenewalSearchBo> {
    constructor() {
        super("renewal");
    }
}

export default new RenewalApi()