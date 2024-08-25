package com.jenson.service.service.clock.impl;

import com.jenson.service.common.basic.SuperServiceImpl;
import com.jenson.service.common.dto.clock.bo.RenewalBo;
import com.jenson.service.common.dto.clock.searchBo.RenewalSearchBo;
import com.jenson.service.common.dto.clock.vo.RenewalVo;
import com.jenson.service.common.dto.clock.Renewal;
import com.jenson.service.common.dto.clock.QRenewal;
import com.jenson.service.mapper.clock.RenewalMapper;
import com.jenson.service.repository.clock.RenewalRepository;
import com.jenson.service.service.clock.RenewalService;

import org.springframework.stereotype.Service;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.jenson.service.common.exception.NotFoundException;
import com.querydsl.jpa.impl.JPAQuery;
import com.jenson.service.util.ObjectUtil;

import javax.annotation.Resource;

@Service
public class RenewalServiceImpl extends SuperServiceImpl<RenewalMapper, Renewal, RenewalVo, RenewalSearchBo> implements RenewalService {
    @Resource
    JPAQueryFactory jpaQueryFactory;

    @Resource
    RenewalMapper renewalMapper;

    @Resource
    RenewalRepository renewalRepository;

    QRenewal qRenewal = QRenewal.renewal;

    @Override
    public Page<RenewalVo> pageAll(RenewalSearchBo searchBo) {
        JPAQuery<Renewal> jpaQuery = jpaQueryFactory
                .selectFrom(qRenewal)
                .where(getWhere(searchBo));

        List<Renewal> renewalList = jpaQuery
                .offset(searchBo.getPageRequest().getOffset())
                .limit(searchBo.getPageRequest().getPageSize())
                .fetch();

        Page<RenewalVo> page = new PageImpl<>(
                ObjectUtil.copyNotNullPropertiesList(renewalList, RenewalVo.class),
                searchBo.getPageRequest(),
                jpaQuery.fetchCount()
        );

        supplement(page);
        return page;
    }

    @Override
    public List<RenewalVo> listAll(RenewalSearchBo searchBo) {
        List<Renewal> renewalList = jpaQueryFactory
                .selectFrom(qRenewal)
                .where(getWhere(searchBo))
                .fetch();

        List<RenewalVo> renewalVoList = ObjectUtil.copyNotNullPropertiesList(renewalList, RenewalVo.class);
        supplement(renewalVoList);
        return renewalVoList;
    }

    @Override
    public RenewalVo get(Integer id) {
        Renewal renewal = renewalRepository.findById(id).orElseThrow(() -> NotFoundException.notFound(id, "权限"));
        RenewalVo renewalVo = ObjectUtil.copyNotNullProperties(renewal, RenewalVo.class);
        supplement(renewalVo);
        return renewalVo;
    }

    @Override
    public RenewalVo edit(RenewalBo renewalBo) {
        Renewal renewal = renewalRepository.findById(renewalBo.getId()).orElse(new Renewal());
        ObjectUtil.copyNotNullProperties(renewalBo, renewal);
        renewal = renewalRepository.save(renewal);
        return ObjectUtil.copyNotNullProperties(renewal, RenewalVo.class);
    }

    @Override
    public List<RenewalVo> edit(List<RenewalBo> renewalBos) {
        List<Renewal> renewalList = renewalRepository.findByIdIn(renewalBos.stream().map(RenewalBo::getId).collect(Collectors.toList()));
        List<Renewal> renewalEntityList = new ArrayList<>();
        for (RenewalBo renewalBo : renewalBos) {
            Renewal renewal = renewalList.stream().filter(_renewal -> Objects.equals(renewalBo.getId(), _renewal.getId())).findFirst().orElse(new Renewal());
            ObjectUtil.copyNotNullProperties(renewalBo, renewal);
            renewalEntityList.add(renewal);
        }
        renewalEntityList = renewalRepository.saveAll(renewalEntityList);
        return ObjectUtil.copyNotNullPropertiesList(renewalEntityList, RenewalVo.class);
    }

    @Override
    public RenewalVo del(Integer id) {
        Renewal renewal = renewalRepository.findById(id).orElseThrow(() -> NotFoundException.notFound(id, "权限"));
        renewal.setDeleted(true);
        renewal = renewalRepository.save(renewal);
        return ObjectUtil.copyNotNullProperties(renewal, RenewalVo.class);
    }

    @Override
    public List<RenewalVo> del(RenewalSearchBo searchBo) {
        List<Renewal> renewalList = renewalRepository.findByIdIn(searchBo.getIdList());
        renewalList = renewalList.stream().peek(it -> it.setDeleted(true)).collect(Collectors.toList());
        renewalList = renewalRepository.saveAll(renewalList);
        return ObjectUtil.copyNotNullPropertiesList(renewalList, RenewalVo.class);
    }

    @Override
    public void supplement(List<RenewalVo> renewalVos) {

    }

    @Override
    public BooleanExpression getWhere(RenewalSearchBo searchBo) {
        return qRenewal.deleted.eq(false);
    }

    @Override
    public RenewalVo getNewVersion() {
        if (Objects.isNull(renewalMapper.getNewVersion())) {
            return null;
        }
        return ObjectUtil.copyNotNullProperties(renewalMapper.getNewVersion(), RenewalVo.class);
    }
}