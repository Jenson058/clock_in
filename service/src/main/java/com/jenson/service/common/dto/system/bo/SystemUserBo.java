package com.jenson.service.common.dto.system.bo;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class SystemUserBo {
    //账号
    private String code;
    //密码
    private String password;
    //旧密码
    private String oldPassword;
    //再次输入密码
    private String subPassword;
}
