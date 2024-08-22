package com.jenson.service.service.user.impl;

import com.jenson.service.common.basic.SuperServiceImpl;
import com.jenson.service.common.dto.user.QRole;
import com.jenson.service.common.dto.user.Role;
import com.jenson.service.common.dto.user.bo.RoleBo;
import com.jenson.service.common.dto.user.searchBo.RoleSearchBo;
import com.jenson.service.common.dto.user.vo.RoleVo;
import com.jenson.service.common.exception.NotFoundException;
import com.jenson.service.mapper.user.RoleMapper;
import com.jenson.service.repository.user.RoleRepository;
import com.jenson.service.service.user.RoleService;
import com.jenson.service.util.ObjectUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends SuperServiceImpl<RoleMapper, Role, RoleVo, RoleSearchBo> implements RoleService {

    @Resource
    JPAQueryFactory jpaQueryFactory;

    @Resource
    RoleMapper roleMapper;

    @Resource
    RoleRepository roleRepository;

    QRole qRole = QRole.role;

    @Override
    public Page<RoleVo> pageAll(RoleSearchBo searchBo) {
        JPAQuery<Role> jpaQuery = jpaQueryFactory
                .selectFrom(qRole)
                .where(getWhere(searchBo));

        List<Role> roleList = jpaQuery
                .offset(searchBo.getPageRequest().getOffset())
                .limit(searchBo.getPageRequest().getPageSize())
                .fetch();

        Page<RoleVo> page = new PageImpl<>(
                ObjectUtil.copyNotNullPropertiesList(roleList, RoleVo.class),
                searchBo.getPageRequest(),
                jpaQuery.fetchCount()
        );

        supplement(page);
        return page;
    }

    @Override
    public List<RoleVo> listAll(RoleSearchBo searchBo) {
        List<Role> roleList = jpaQueryFactory
                .selectFrom(qRole)
                .where(getWhere(searchBo))
                .fetch();

        List<RoleVo> roleVoList = ObjectUtil.copyNotNullPropertiesList(roleList, RoleVo.class);
        supplement(roleVoList);
        return roleVoList;
    }

    @Override
    public RoleVo get(Integer id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> NotFoundException.notFound(id, "权限"));
        RoleVo roleVo = ObjectUtil.copyNotNullProperties(role, RoleVo.class);
        supplement(roleVo);
        return roleVo;
    }

    @Override
    public RoleVo edit(RoleBo roleBo) {
        Role role = roleRepository.findById(roleBo.getId()).orElse(new Role());
        ObjectUtil.copyNotNullProperties(roleBo, role);
        role = roleRepository.save(role);
        return ObjectUtil.copyNotNullProperties(role, RoleVo.class);
    }

    @Override
    public List<RoleVo> edit(List<RoleBo> roleBos) {
        List<Role> roleList = roleRepository.findByIdIn(roleBos.stream().map(RoleBo::getId).collect(Collectors.toList()));
        List<Role> roleEntityList = new ArrayList<>();
        for (RoleBo roleBo : roleBos) {
            Role role = roleList.stream().filter(_role -> Objects.equals(roleBo.getId(), _role.getId())).findFirst().orElse(new Role());
            ObjectUtil.copyNotNullProperties(roleBo, role);
            roleEntityList.add(role);
        }
        roleEntityList = roleRepository.saveAll(roleEntityList);
        return ObjectUtil.copyNotNullPropertiesList(roleEntityList, RoleVo.class);
    }

    @Override
    public RoleVo del(Integer id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> NotFoundException.notFound(id, "权限"));
        role.setDeleted(true);
        role = roleRepository.save(role);
        return ObjectUtil.copyNotNullProperties(role, RoleVo.class);
    }

    @Override
    public List<RoleVo> del(RoleSearchBo searchBo) {
        List<Role> roleList = roleRepository.findByIdIn(searchBo.getIdList());
        roleList = roleList.stream().peek(it -> it.setDeleted(true)).collect(Collectors.toList());
        roleList = roleRepository.saveAll(roleList);
        return ObjectUtil.copyNotNullPropertiesList(roleList, RoleVo.class);
    }

    @Override
    public void supplement(List<RoleVo> roleVos) {

    }

    @Override
    public BooleanExpression getWhere(RoleSearchBo searchBo) {
        return qRole.deleted.eq(false);
    }
}
