package com.jenson.service.common.dto.user;

import com.jenson.service.common.basic.SuperEntity;
import com.jenson.service.common.convert.IntegerListConvert;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Convert;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Builder
@EqualsAndHashCode(callSuper = true)
public class User extends SuperEntity {
    //账号
    private String code;
    //姓名
    private String name;
    //昵称
    private String nickname;
    //密码
    private String password;
    //密码加密段
    private String encipher;
    //是否为管理员
    private Boolean isAdmin;
    // 出生日期
    private Date birthday;
    //性别
    private String sex;
    //头像
    private String avatar;
    //手机号
    private String phone;
    //邮箱
    private String e_mail;

    @Convert(converter = IntegerListConvert.class)
    private List<Integer> roleList;

}
