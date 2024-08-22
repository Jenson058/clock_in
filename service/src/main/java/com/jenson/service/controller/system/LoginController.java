package com.jenson.service.controller.system;

import com.jenson.service.common.basic.Result;
import com.jenson.service.common.dto.system.bo.SystemUserBo;
import com.jenson.service.common.dto.system.vo.SystemUserVo;
import com.jenson.service.service.system.SystemLoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@RequestMapping("/api/system")
public class LoginController {

    @Resource
    private SystemLoginService systemLoginService;

    @PostMapping("/login")
    public Result<SystemUserVo> systemLogin(@RequestBody SystemUserBo systemUserBo){
        return Result.suc(systemLoginService.systemLogin(systemUserBo));
    }

    @PostMapping("/update_password")
    public Result<Boolean> updatePassword(@RequestBody SystemUserBo systemUserBo){
        return Result.suc(systemLoginService.updatePassword(systemUserBo));
    }


}
