package com.jenson.service.service.user;

import com.jenson.service.common.basic.SuperService;
import com.jenson.service.common.dto.user.User;
import com.jenson.service.common.dto.user.bo.UserBo;
import com.jenson.service.common.dto.user.searchBo.UserSearchBo;
import com.jenson.service.common.dto.user.vo.UserVo;

import java.util.List;

public interface UserService extends SuperService<UserVo, UserBo, UserSearchBo> {

    UserVo updatedPassword(UserBo userBo);
}
