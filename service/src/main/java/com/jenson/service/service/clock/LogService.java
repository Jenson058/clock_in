package com.jenson.service.service.clock;

import com.jenson.service.common.basic.SuperService;
import com.jenson.service.common.dto.clock.bo.LogBo;
import com.jenson.service.common.dto.clock.searchBo.LogSearchBo;
import com.jenson.service.common.dto.clock.vo.LogVo;
public interface LogService extends SuperService<LogVo, LogBo, LogSearchBo> {
    LogVo clockIn(LogBo logBo);

    Boolean toDayIsClockIn(Integer userId);
}