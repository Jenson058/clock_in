// @ts-nocheck
import SuperApi from "@/common/basic/SuperApi";
import {LogBo, LogSearchBo, LogVo} from "@/common/dto/clock/Log";
import {GetMapping, HttpMapping, PostMapping, RequestBody, RequestParam, useRequest} from "@/config/request";
import Result from "@/common/basic/Result";
import {FileVo} from "@/common/dto/file/File";
import {axiosRequest} from "@/config/axios";

class LogApi extends SuperApi<LogVo,LogBo,LogSearchBo>{

    constructor() {
        super("log");
    }

    async clockIn(logBo:LogBo){
        return await axiosRequest.post<LogBo,Result<LogVo>>(`/log/clock_in`,logBo)
    }

    async toDayIsClockIn(logSearchBo:LogSearchBo){
        return await axiosRequest.post<LogBo,Result<boolean>>(`/log/to_day`,logSearchBo);
    }
}

export default new LogApi()