package com.jenson.service.mapper.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenson.service.common.dto.user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
