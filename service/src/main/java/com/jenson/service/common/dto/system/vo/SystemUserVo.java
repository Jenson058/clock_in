package com.jenson.service.common.dto.system.vo;

import com.jenson.service.common.dto.user.vo.UserVo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
public class SystemUserVo {
    private UserVo userVo;
    private String token;
}
