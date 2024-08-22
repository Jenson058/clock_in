package com.jenson.service.common.dto.user.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class UserBo {
    private Integer id;
    //账号
    private String code;
    //姓名
    private String name;
    //昵称
    private String nickname;
    //密码
    private String password;
    //性别
    private String sex;
    // 出生日期
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    //头像
    private String avatar;
    //手机号
    private String phone;
    //邮箱
    private String e_mail;
    //角色
    private List<Integer> roleList;
}
