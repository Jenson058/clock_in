package com.jenson.service.service.file;

import com.jenson.service.common.basic.SuperService;
import com.jenson.service.common.dto.file.bo.FileBo;
import com.jenson.service.common.dto.file.searchBo.FileSearchBo;
import com.jenson.service.common.dto.file.vo.FileVo;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface FileService extends SuperService<FileVo, FileBo, FileSearchBo> {
    FileVo upload(FileBo fileBo) throws IOException;

    ResponseEntity<InputStreamResource> download(Integer id) throws IOException;
}
