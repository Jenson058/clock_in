// @ts-nocheck
import SuperApi from "@/common/basic/SuperApi";
import {LogBo, LogSearchBo, LogVo} from "@/common/dto/clock/Log";
import {GetMapping, HttpMapping, PostMapping, RequestBody, RequestParam, useRequest} from "@/config/request";
import Result from "@/common/basic/Result";

@HttpMapping("/log")
class LogApi extends SuperApi<LogVo,LogBo,LogSearchBo>{

    @PostMapping("/clock_in")
    async clockIn(@RequestBody() logBo:LogBo):Promise<Result<LogVo> | undefined>{
        return undefined;
    }

    @PostMapping("/to_day")
    async toDayIsClockIn(@RequestBody() logSearchBo:LogSearchBo):Promise<Result<boolean> | undefined>{
        return undefined;
    }

}

export default useRequest<LogApi>(LogApi)