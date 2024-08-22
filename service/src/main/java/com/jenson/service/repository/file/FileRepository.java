package com.jenson.service.repository.file;

import com.jenson.service.common.basic.SuperRepository;
import com.jenson.service.common.dto.file.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends SuperRepository<File>, JpaRepository<File,Long> {

    Optional<File> findByMdKey(String mdKey);

}
