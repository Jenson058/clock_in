package com.jenson.service.service.clock;

import com.jenson.service.common.basic.SuperService;
import com.jenson.service.common.dto.clock.bo.RenewalBo;
import com.jenson.service.common.dto.clock.searchBo.RenewalSearchBo;
import com.jenson.service.common.dto.clock.vo.RenewalVo;
public interface RenewalService extends SuperService<RenewalVo, RenewalBo, RenewalSearchBo> {
    RenewalVo getNewVersion();
}