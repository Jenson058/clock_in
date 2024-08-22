package com.jenson.service.service.clock.impl;

import com.jenson.service.common.basic.SuperServiceImpl;
import com.jenson.service.common.dto.clock.bo.LogBo;
import com.jenson.service.common.dto.clock.searchBo.LogSearchBo;
import com.jenson.service.common.dto.clock.vo.LogVo;
import com.jenson.service.common.dto.clock.Log;
import com.jenson.service.common.dto.clock.QLog;
import com.jenson.service.common.dto.user.User;
import com.jenson.service.mapper.clock.LogMapper;
import com.jenson.service.repository.clock.LogRepository;
import com.jenson.service.repository.user.UserRepository;
import com.jenson.service.service.clock.LogService;

import com.jenson.service.util.ListUtil;
import org.springframework.stereotype.Service;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.ArrayList;
import java.util.Calendar;
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
public class LogServiceImpl extends SuperServiceImpl<LogMapper, Log, LogVo, LogSearchBo> implements LogService {
    @Resource
    JPAQueryFactory jpaQueryFactory;

    @Resource
    LogMapper logMapper;

    @Resource
    LogRepository logRepository;

    @Resource
    UserRepository userRepository;

    QLog qLog = QLog.log;

    @Override
    public Page<LogVo> pageAll(LogSearchBo searchBo) {
        JPAQuery<Log> jpaQuery = jpaQueryFactory
                .selectFrom(qLog)
                .where(getWhere(searchBo))
                .orderBy(qLog.updatedAt.asc());

        List<Log> logList = jpaQuery
                .offset(searchBo.getPageRequest().getOffset())
                .limit(searchBo.getPageRequest().getPageSize())
                .fetch();

        Page<LogVo> page = new PageImpl<>(
                ObjectUtil.copyNotNullPropertiesList(logList, LogVo.class),
                searchBo.getPageRequest(),
                jpaQuery.fetchCount()
        );

        supplement(page);
        return page;
    }

    @Override
    public List<LogVo> listAll(LogSearchBo searchBo) {
        List<Log> logList = jpaQueryFactory
                .selectFrom(qLog)
                .where(getWhere(searchBo))
                .fetch();

        List<LogVo> logVoList = ObjectUtil.copyNotNullPropertiesList(logList, LogVo.class);
        supplement(logVoList);
        return logVoList;
    }

    @Override
    public LogVo get(Integer id) {
        Log log = logRepository.findById(id).orElseThrow(() -> NotFoundException.notFound(id, "权限"));
        LogVo logVo = ObjectUtil.copyNotNullProperties(log, LogVo.class);
        supplement(logVo);
        return logVo;
    }

    @Override
    public LogVo edit(LogBo logBo) {
        Log log = logRepository.findById(logBo.getId()).orElse(new Log());
        ObjectUtil.copyNotNullProperties(logBo, log);
        log = logRepository.save(log);
        return ObjectUtil.copyNotNullProperties(log, LogVo.class);
    }

    @Override
    public List<LogVo> edit(List<LogBo> logBos) {
        List<Log> logList = logRepository.findByIdIn(logBos.stream().map(LogBo::getId).collect(Collectors.toList()));
        List<Log> logEntityList = new ArrayList<>();
        for (LogBo logBo : logBos) {
            Log log = logList.stream().filter(_log -> Objects.equals(logBo.getId(), _log.getId())).findFirst().orElse(new Log());
            ObjectUtil.copyNotNullProperties(logBo, log);
            logEntityList.add(log);
        }
        logEntityList = logRepository.saveAll(logEntityList);
        return ObjectUtil.copyNotNullPropertiesList(logEntityList, LogVo.class);
    }

    @Override
    public LogVo del(Integer id) {
        Log log = logRepository.findById(id).orElseThrow(() -> NotFoundException.notFound(id, "权限"));
        log.setDeleted(true);
        log = logRepository.save(log);
        return ObjectUtil.copyNotNullProperties(log, LogVo.class);
    }

    @Override
    public List<LogVo> del(LogSearchBo searchBo) {
        List<Log> logList = logRepository.findByIdIn(searchBo.getIdList());
        logList = logList.stream().peek(it -> it.setDeleted(true)).collect(Collectors.toList());
        logList = logRepository.saveAll(logList);
        return ObjectUtil.copyNotNullPropertiesList(logList, LogVo.class);
    }

    @Override
    public void supplement(List<LogVo> logVos) {
        List<User> userList = userRepository.findByIdIn(logVos.stream().map(LogVo::getUserId).collect(Collectors.toList()));
        logVos.forEach(log -> {
            User user = userList.stream().filter(it -> Objects.equals(it.getId(),log.getUserId())).findFirst().orElse(new User());


            log.setUserName(user.getName());
        });
    }

    @Override
    public BooleanExpression getWhere(LogSearchBo searchBo) {
        BooleanExpression where = qLog.deleted.eq(false);
        if (Objects.nonNull(searchBo.getUserId())){
            where = where.and(qLog.userId.eq(searchBo.getUserId()));
        }
        if (Objects.nonNull(searchBo.getYear())){
            where = where.and(qLog.year.eq(searchBo.getYear()));
        }
        if (Objects.nonNull(searchBo.getMouth())){
            where = where.and(qLog.mouth.eq(searchBo.getMouth()));
        }
        return where;
    }

    @Override
    public LogVo clockIn(LogBo logBo) {
        Calendar calendar = Calendar.getInstance();
        Log log = new Log()
                .setUserId(logBo.getUserId())
                .setYear(calendar.get(Calendar.YEAR))
                .setMouth(calendar.get(Calendar.MONTH) + 1)
                .setDay(calendar.get(Calendar.DAY_OF_MONTH));
        log = logRepository.save(log);
        return ObjectUtil.copyNotNullProperties(log,LogVo.class);
    }

    @Override
    public Boolean toDayIsClockIn(Integer userId) {
        Calendar calendar = Calendar.getInstance();
        List<Log> logList = logRepository.findByYearAndMouthAndDayAndUserId(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH),
                userId
        );
        return !ListUtil.isEmpty(logList);
    }

}