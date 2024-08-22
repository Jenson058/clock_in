package com.jenson.service.repository.clock;

import com.jenson.service.common.basic.SuperRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jenson.service.common.dto.clock.Log;

import java.util.List;

@Repository
public interface LogRepository extends SuperRepository<Log>, JpaRepository<Log, Long> {

    List<Log> findByYearAndMouthAndDayAndUserId(Integer year, Integer mouth, Integer day, Integer userId);

}