package com.jenson.service.service.user;

import com.jenson.service.common.basic.KeyValue;

import java.util.List;

public interface AuthService{

    List<KeyValue<String,List<String>>> authAll();

}
