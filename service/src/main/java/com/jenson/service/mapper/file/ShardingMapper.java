package com.jenson.service.mapper.file;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jenson.service.common.dto.file.Sharding;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ShardingMapper extends BaseMapper<Sharding> {
}
