package com.jenson.service.controller.file;

import com.jenson.service.common.basic.Result;
import com.jenson.service.common.basic.SuperController;
import com.jenson.service.common.dto.file.bo.FileBo;
import com.jenson.service.common.dto.file.searchBo.FileSearchBo;
import com.jenson.service.common.dto.file.vo.FileVo;
import com.jenson.service.service.file.FileService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController extends SuperController<FileVo, FileBo, FileSearchBo, FileService> {

    @Resource
    private FileService fileService;

    @PostMapping("/upload")
    public Result<FileVo> upload(@RequestPart("file")MultipartFile file,@RequestPart("fileBo") FileBo fileBo) throws IOException {
        fileBo.setFile(file);
        return Result.suc(fileService.upload(fileBo));
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamResource> download(@RequestParam("id") Integer id) throws IOException {
        return fileService.download(id);
    }

}
