package com.jenson.service.controller.clock;

import com.jenson.service.common.basic.Result;
import com.jenson.service.common.basic.SuperController;
import org.springframework.web.bind.annotation.*;
import com.jenson.service.common.dto.clock.bo.LogBo;
import com.jenson.service.common.dto.clock.searchBo.LogSearchBo;
import com.jenson.service.common.dto.clock.vo.LogVo;
import com.jenson.service.service.clock.LogService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/log")
public class LogController extends SuperController<LogVo, LogBo, LogSearchBo, LogService> {
    @Resource
    LogService logService;

    @PostMapping("/clock_in")
    public Result<LogVo> clockIn(@RequestBody LogBo logBo){
        return Result.suc(logService.clockIn(logBo));
    }

    @PostMapping("/to_day")
    public Result<Boolean> toDayIsClockIn(@RequestBody LogSearchBo logSearchBo){
        return Result.suc(logService.toDayIsClockIn(logSearchBo.getUserId()));
    }
}