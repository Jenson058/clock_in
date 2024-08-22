package com.jenson.service.repository.file;

import com.jenson.service.common.basic.SuperRepository;
import com.jenson.service.common.dto.file.Sharding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShardingRepository extends SuperRepository<Sharding>, JpaRepository<Sharding,Long> {

    List<Sharding> findByFileId(Integer fileId);

}
