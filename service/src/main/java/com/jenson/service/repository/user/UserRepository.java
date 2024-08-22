package com.jenson.service.repository.user;

import com.jenson.service.common.basic.SuperRepository;
import com.jenson.service.common.dto.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends SuperRepository<User>, JpaRepository<User, Long> {

    Optional<User> findByCode(String code);

}
