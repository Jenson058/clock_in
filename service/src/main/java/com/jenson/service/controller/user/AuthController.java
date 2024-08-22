package com.jenson.service.controller.user;

import com.jenson.service.common.basic.KeyValue;
import com.jenson.service.common.basic.Result;
import com.jenson.service.service.user.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    AuthService authService;

    @GetMapping("/all")
    public Result<List<KeyValue<String, List<String>>>> all() {
        return Result.suc(authService.authAll());
    }


}
