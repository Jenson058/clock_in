package com.jenson.service.controller.user;

import com.jenson.service.common.basic.Result;
import com.jenson.service.common.basic.SuperController;
import com.jenson.service.common.dto.user.bo.UserBo;
import com.jenson.service.common.dto.user.searchBo.UserSearchBo;
import com.jenson.service.common.dto.user.vo.UserVo;
import com.jenson.service.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
public class UserController extends SuperController<UserVo, UserBo, UserSearchBo, UserService> {

    @Resource
    UserService userService;

    @PostMapping("/updated_password")
    public Result<UserVo> updatedPassword(@RequestBody UserBo userBo){
        return Result.suc(userService.updatedPassword(userBo));
    }

}
