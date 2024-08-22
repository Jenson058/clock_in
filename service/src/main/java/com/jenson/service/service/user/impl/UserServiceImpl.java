package com.jenson.service.service.user.impl;

import com.jenson.service.common.basic.SuperServiceImpl;
import com.jenson.service.common.dto.user.QUser;
import com.jenson.service.common.dto.user.Role;
import com.jenson.service.common.dto.user.User;
import com.jenson.service.common.dto.user.bo.UserBo;
import com.jenson.service.common.dto.user.searchBo.UserSearchBo;
import com.jenson.service.common.dto.user.vo.UserVo;
import com.jenson.service.common.exception.NotFoundException;
import com.jenson.service.mapper.user.UserMapper;
import com.jenson.service.repository.user.RoleRepository;
import com.jenson.service.repository.user.UserRepository;
import com.jenson.service.service.user.UserService;
import com.jenson.service.util.ListUtil;
import com.jenson.service.util.MD5Util;
import com.jenson.service.util.ObjectUtil;
import com.jenson.service.util.StringUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends SuperServiceImpl<UserMapper, User, UserVo, UserSearchBo> implements UserService {

    @Resource
    JPAQueryFactory jpaQueryFactory;

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleRepository roleRepository;

    QUser qUser = QUser.user;

    @Override
    public Page<UserVo> pageAll(UserSearchBo searchBo) {
        JPAQuery<User> jpaQuery = jpaQueryFactory
                .selectFrom(qUser)
                .where(getWhere(searchBo));

        List<User> userList = jpaQuery
                .offset(searchBo.getPageRequest().getOffset())
                .limit(searchBo.getPageRequest().getPageSize())
                .fetch();

        Page<UserVo> page = new PageImpl<>(
                ObjectUtil.copyNotNullPropertiesList(userList, UserVo.class),
                searchBo.getPageRequest(),
                jpaQuery.fetchCount()
        );
        supplement(page);
        return page;
    }

    @Override
    public List<UserVo> listAll(UserSearchBo searchBo) {

        List<User> userList = jpaQueryFactory
                .selectFrom(qUser)
                .where(getWhere(searchBo))
                .fetch();

        List<UserVo> userVoList = ObjectUtil.copyNotNullPropertiesList(userList, UserVo.class);
        supplement(userVoList);
        return userVoList;
    }

    @Override
    public UserVo get(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> NotFoundException.notFound(id, "用户"));
        UserVo userVo = ObjectUtil.copyNotNullProperties(user, UserVo.class);
        supplement(userVo);
        return userVo;
    }

    @Override
    public UserVo edit(UserBo userBo) {
        User user = userRepository.findById(userBo.getId()).orElse(new User());
        List<User> userList = userRepository.findAll();
        ObjectUtil.copyNotNullProperties(userBo, user);
        if (Objects.isNull(user.getId())) {
            user.setCode(StringUtil.coverPosition("J", 4, userList.size()));
            if (Objects.isNull(userBo.getPassword()))
                user.setPassword("123456");
            user.setEncipher(MD5Util.random16String());
            user.setPassword(MD5Util.cryptographicString(user.getPassword(), user.getEncipher()));
        }
        user = userRepository.save(user);
        return ObjectUtil.copyNotNullProperties(user, UserVo.class);
    }

    @Override
    public List<UserVo> edit(List<UserBo> userBos) {
        List<User> userList = userRepository.findByIdIn(userBos.stream().map(UserBo::getId).collect(Collectors.toList()));
        List<User> userSuList = userRepository.findAll();
        List<User> userEntityList = new ArrayList<>();
        for (UserBo it : userBos) {
            User user = userList.stream().filter(_user -> Objects.equals(it.getId(), _user.getId())).findFirst().orElse(new User());
            ObjectUtil.copyNotNullProperties(it, user);
            if (Objects.isNull(user.getId())) {
                user.setCode(StringUtil.coverPosition("J", 4, userSuList.size()));
                if (Objects.isNull(it.getPassword()))
                    user.setPassword("123456");
                user.setEncipher(MD5Util.random16String());
                user.setPassword(MD5Util.cryptographicString(user.getPassword(), user.getEncipher()));
            }
            userEntityList.add(user);
        }
        userEntityList = userRepository.saveAll(userEntityList);
        return ObjectUtil.copyNotNullPropertiesList(userEntityList, UserVo.class);
    }

    @Override
    public UserVo del(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> NotFoundException.notFound(id, "用户"));
        user.setDeleted(true);
        user = userRepository.save(user);
        return ObjectUtil.copyNotNullProperties(user, UserVo.class);
    }

    @Override
    public List<UserVo> del(UserSearchBo searchBo) {
        List<User> userList = userRepository.findByIdIn(searchBo.getIdList());
        userList = userList.stream().peek(it -> it.setDeleted(true)).collect(Collectors.toList());
        userList = userRepository.saveAll(userList);
        return ObjectUtil.copyNotNullPropertiesList(userList, UserVo.class);
    }

    @Override
    public void supplement(List<UserVo> userVos) {

        Calendar calendar = Calendar.getInstance();

        List<Role> roleList = roleRepository.findByIdIn(
                ListUtil.multilevelNesting(
                        userVos.stream().map(UserVo::getRoleList).collect(Collectors.toList())
                ).stream().distinct().collect(Collectors.toList())
        );

        userVos.forEach(it -> {

            Calendar birthday = Calendar.getInstance();
            birthday.setTime(it.getBirthday());

            List<String> roles = ListUtil
                    .multilevelNesting(
                            roleList
                                    .stream()
                                    .filter(role -> ListUtil.exist(it.getRoleList(), role.getId()))
                                    .map(Role::getAuthAll)
                                    .collect(Collectors.toList())
                    );

            it.setAuthList(roles);
            it.setAge(calendar.get(Calendar.YEAR) - birthday.get(Calendar.YEAR) + 1);

        });
    }

    @Override
    public BooleanExpression getWhere(UserSearchBo searchBo) {
        BooleanExpression where = qUser.deleted.eq(false);
        where = where.and(qUser.code.ne("admin"));
        return where;
    }

    @Override
    public UserVo updatedPassword(UserBo userBo) {
        User user = userRepository.findById(userBo.getId()).orElseThrow(() -> NotFoundException.notFound(userBo.getId(), "用户"));
        if (Objects.isNull(userBo.getPassword()))
            user.setPassword("123456");
        user.setEncipher(MD5Util.random16String());
        user.setPassword(MD5Util.cryptographicString(user.getPassword(), user.getEncipher()));
        user = userRepository.save(user);
        return ObjectUtil.copyNotNullProperties(user, UserVo.class);
    }
}
