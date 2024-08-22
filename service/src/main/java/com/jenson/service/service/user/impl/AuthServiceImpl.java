package com.jenson.service.service.user.impl;

import com.jenson.service.common.basic.KeyValue;
import com.jenson.service.common.enumm.AuthEnum;
import com.jenson.service.service.user.AuthService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService{

    @Override
    public List<KeyValue<String, List<String>>> authAll() {
        return AuthEnum.getValues();
    }
}
