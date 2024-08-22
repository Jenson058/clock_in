package com.jenson.service.common.enumm;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TranslateEnum {

    //模块
    user("user"),
    auth("auth"),
    role("role"),
    clock("clock"),

    //操作
    add("add"),
    edit("edit"),
    del("delete"),
    ret("retrieve");

    private final String value;

}
