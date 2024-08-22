package com.jenson.service.common.enumm;

import com.jenson.service.common.basic.KeyValue;
import com.jenson.service.common.dto.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum AuthEnum {

    //用户增删改查权限
    User(TranslateEnum.user, new TranslateEnum[]{TranslateEnum.add, TranslateEnum.del, TranslateEnum.edit, TranslateEnum.ret}),
    Auth(TranslateEnum.auth, new TranslateEnum[]{TranslateEnum.edit,TranslateEnum.ret}),
    Role(TranslateEnum.role, new TranslateEnum[]{TranslateEnum.add, TranslateEnum.del, TranslateEnum.edit, TranslateEnum.ret}),
    Clock(TranslateEnum.clock,new TranslateEnum[]{TranslateEnum.ret});

    private final TranslateEnum title;
    private final TranslateEnum[] auth;

    public static List<KeyValue<String, List<String>>> getValues() {
        List<KeyValue<String, List<String>>> list = new ArrayList<>();
        for (AuthEnum value : AuthEnum.values()) {
            list.add(
                    new KeyValue<>(
                            value.title.getValue(),
                            Arrays.stream(value.auth).map(TranslateEnum::getValue).collect(Collectors.toList())
                    )
            );
        }
        return list;
    }
}
