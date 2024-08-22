package com.jenson.service.service.system.impl;

import com.jenson.service.common.dto.system.bo.SystemUserBo;
import com.jenson.service.common.dto.system.vo.SystemUserVo;
import com.jenson.service.common.dto.user.Role;
import com.jenson.service.common.dto.user.User;
import com.jenson.service.common.dto.user.vo.UserVo;
import com.jenson.service.common.exception.PasswordNotPassException;
import com.jenson.service.repository.user.RoleRepository;
import com.jenson.service.repository.user.UserRepository;
import com.jenson.service.service.system.SystemLoginService;
import com.jenson.service.util.JWTUtil;
import com.jenson.service.util.ListUtil;
import com.jenson.service.util.MD5Util;
import com.jenson.service.util.ObjectUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SystemLoginServiceImpl implements SystemLoginService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    @Override
    public SystemUserVo systemLogin(SystemUserBo systemUserBo) {
        if (Objects.isNull(systemUserBo.getPassword()))
            throw PasswordNotPassException.err("密码未填");
        User user = userRepository.findByCode(systemUserBo.getCode()).orElseThrow(() -> PasswordNotPassException.err("账号或密码不正确"));
        String password = MD5Util.cryptographicString(systemUserBo.getPassword(), user.getEncipher());
        if (!password.equals(user.getPassword())) {
            throw PasswordNotPassException.err("账号或密码不正确");
        }

        UserVo userVo = ObjectUtil.copyNotNullProperties(user, UserVo.class);
        if (ListUtil.isEmpty(userVo.getRoleList())){
            return new SystemUserVo().setUserVo(userVo).setToken(JWTUtil.getToken(userVo));
        }
        List<Role> roleList = roleRepository.findByIdIn(userVo.getRoleList());
        List<String> authList = ListUtil.multilevelNesting(roleList.stream().map(Role::getAuthAll).collect(Collectors.toList()));
        userVo.setAuthList(authList);
        return new SystemUserVo().setUserVo(userVo).setToken(JWTUtil.getToken(userVo));
    }

    @Override
    public Boolean updatePassword(SystemUserBo systemUserBo) {
        if (Objects.isNull(systemUserBo.getPassword()) || Objects.isNull(systemUserBo.getSubPassword())) {
            throw PasswordNotPassException.err("两次新密码不正确");
        }
        if (!Objects.equals(systemUserBo.getPassword(), systemUserBo.getSubPassword())) {
            throw PasswordNotPassException.err("两次新密码不正确");
        }

        User user = userRepository.findByCode(systemUserBo.getCode()).orElseThrow(() -> PasswordNotPassException.err("账号或密码不正确"));
        String password = MD5Util.cryptographicString(systemUserBo.getOldPassword(), user.getEncipher());
        if (!password.equals(user.getPassword())) {
            throw PasswordNotPassException.err("账号或密码不正确");
        }
        user.setPassword(
                MD5Util.cryptographicString(systemUserBo.getPassword(), user.getEncipher())
        );
        userRepository.save(user);
        return true;
    }

}
