package com.jenson.service.service.system;

import com.jenson.service.common.dto.system.bo.SystemUserBo;
import com.jenson.service.common.dto.system.vo.SystemUserVo;

public interface SystemLoginService {

    SystemUserVo systemLogin(SystemUserBo systemUserBo);

    Boolean updatePassword(SystemUserBo systemUserBo);
}
