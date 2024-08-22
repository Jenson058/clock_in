package com.jenson.service.repository.clock;

import com.jenson.service.common.basic.SuperRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jenson.service.common.dto.clock.Renewal;

@Repository
public interface RenewalRepository extends SuperRepository<Renewal>, JpaRepository<Renewal,Long> {
}