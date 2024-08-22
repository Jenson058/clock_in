package com.jenson.service.repository.user;

import com.jenson.service.common.basic.SuperRepository;
import com.jenson.service.common.dto.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends SuperRepository<Role>, JpaRepository<Role,Long> {
}
