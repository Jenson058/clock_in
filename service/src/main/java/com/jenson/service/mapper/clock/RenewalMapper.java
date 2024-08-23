package com.jenson.service.mapper.clock;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.jenson.service.common.dto.clock.Renewal;

@Mapper
public interface RenewalMapper extends BaseMapper<Renewal>{

    Renewal getNewVersion();
}