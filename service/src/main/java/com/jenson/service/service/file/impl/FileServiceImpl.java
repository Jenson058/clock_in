package com.jenson.service.service.file.impl;

import com.jenson.service.common.basic.SuperServiceImpl;
import com.jenson.service.common.dto.file.File;
import com.jenson.service.common.dto.file.QFile;
import com.jenson.service.common.dto.file.Sharding;
import com.jenson.service.common.dto.file.bo.FileBo;
import com.jenson.service.common.dto.file.searchBo.FileSearchBo;
import com.jenson.service.common.dto.file.vo.FileVo;
import com.jenson.service.common.exception.BusinessException;
import com.jenson.service.common.exception.NotFoundException;
import com.jenson.service.common.system.ShareChar;
import com.jenson.service.mapper.file.FileMapper;
import com.jenson.service.repository.file.FileRepository;
import com.jenson.service.repository.file.ShardingRepository;
import com.jenson.service.service.file.FileService;
import com.jenson.service.util.FileUtil;
import com.jenson.service.util.ObjectUtil;
import com.jenson.service.util.StringUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl extends SuperServiceImpl<FileMapper, File, FileVo, FileSearchBo> implements FileService {

    @Resource
    FileMapper fileMapper;

    @Resource
    FileRepository fileRepository;

    @Resource
    ShardingRepository shardingRepository;

    @Resource
    JPAQueryFactory jpaQueryFactory;

    QFile qFile = QFile.file;

    @Override
    public Page<FileVo> pageAll(FileSearchBo searchBo) {
        return null;
    }

    @Override
    public List<FileVo> listAll(FileSearchBo searchBo) {
        List<File> fileList = jpaQueryFactory
                .selectFrom(qFile)
                .where(getWhere(searchBo))
                .fetch();

        List<FileVo> fileVoList = ObjectUtil.copyNotNullPropertiesList(fileList, FileVo.class);
        supplement(fileVoList);
        return fileVoList;
    }

    @Override
    public FileVo get(Integer id) {
        File file = fileRepository.findById(id)
                .orElseThrow(() -> NotFoundException.notFound(id, "文件"));
        return ObjectUtil.copyNotNullProperties(file, FileVo.class);
    }

    @Override
    public FileVo edit(FileBo fileBo) {
        return null;
    }

    @Override
    public List<FileVo> edit(List<FileBo> fileBos) {
        return null;
    }

    @Override
    public FileVo del(Integer id) {
        return null;
    }

    @Override
    public List<FileVo> del(FileSearchBo searchBo) {
        return null;
    }

    @Override
    public void supplement(List<FileVo> fileVos) {

    }

    @Override
    public BooleanExpression getWhere(FileSearchBo searchBo) {
        BooleanExpression where = qFile.deleted.eq(false);
        if (Objects.nonNull(searchBo.getIdList()))
            where = where.and(qFile.id.in(searchBo.getIdList()));
        return where;
    }

    @Override
    public FileVo upload(FileBo fileBo) throws IOException {
        File file = fileRepository.findByMdKey(fileBo.getMdKey()).orElse(null);
        if (Objects.nonNull(file) && file.getFinish()) {
            return ObjectUtil
                    .copyNotNullProperties(file, FileVo.class)
                    .setNumber_(fileBo.getCount())
                    .setCount(fileBo.getCount());
        } else if (Objects.isNull(file)) {
            file = ObjectUtil.copyNotNullProperties(fileBo, File.class)
                    .setFilePath(
                            ShareChar.getUploadPath() +
                                    fileBo.getMdKey() +
                                    "." +
                                    fileBo.getSuffix()
                    )
                    .setFinish(false);

            file = fileRepository.save(file);
        }

        if (fileBo.getFile().isEmpty())
            throw BusinessException.err("文件不能为空");
        if (!FileUtil.checkDirExists(ShareChar.getUploadPath())) {
            throw BusinessException.err("上传目录不存在");
        }

        Sharding sharding = new Sharding()
                .setFileId(file.getId())
                .setNumber(fileBo.getNumber_())
                .setCount(fileBo.getCount())
                .setFilePath(ShareChar.getUploadPath() + StringUtil.randomString(32));

        java.io.File localFile = new java.io.File(sharding.getFilePath());
        fileBo.getFile().transferTo(localFile);

        sharding = shardingRepository.save(sharding);

        if (!Objects.equals(sharding.getCount(), sharding.getNumber())) {
            return ObjectUtil.copyNotNullProperties(file, FileVo.class)
                    .setCount(fileBo.getCount())
                    .setNumber_(fileBo.getNumber_());
        }

        List<Sharding> shardingList = shardingRepository.findByFileId(file.getId());
        if (!FileUtil.mergeFile(file.getFilePath(), shardingList.stream().map(Sharding::getFilePath).collect(Collectors.toList()))) {
            throw BusinessException.err("合并文件失败");
        }

        shardingList = shardingList.stream().peek(it -> it.setDeleted(true)).collect(Collectors.toList());
        shardingRepository.saveAll(shardingList);
        file = fileRepository.save(file.setFinish(true));
        return ObjectUtil.copyNotNullProperties(file, FileVo.class)
                .setCount(fileBo.getCount())
                .setNumber_(fileBo.getNumber_());
    }

    @Override
    public ResponseEntity<InputStreamResource> download(Integer id) throws IOException {
        FileVo fileVo = get(id);
        String filePath = fileVo.getFilePath();
        FileSystemResource file = new FileSystemResource(filePath);
        return getInputStreamResourceResponseEntity(file, fileVo.getOldFileName());
    }

    private ResponseEntity<InputStreamResource> getInputStreamResourceResponseEntity(FileSystemResource file) throws IOException {
        return getInputStreamResourceResponseEntity(file, null);
    }

    private ResponseEntity<InputStreamResource> getInputStreamResourceResponseEntity(FileSystemResource file, String fileName) throws IOException {

        String name = fileName;
        if (Objects.isNull(name)) {
            name = URLEncoder.encode(
                    Objects.requireNonNull(file.getFilename()),
                    String.valueOf(StandardCharsets.UTF_8)
            );
        }
        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl("no-cache, no-store, must-revalidate");
        headers.setContentDisposition(
                ContentDisposition
                        .builder("inline")
                        .filename(name)
                        .build()
        );
        headers.setPragma("no-cache");
        headers.setExpires(0);

        MediaType mediaType;

        try {
            // 根据文件 url 猜测 ContentType
            mediaType = MediaType.parseMediaType(URLConnection.guessContentTypeFromName(file.getFilename()));
        } catch (InvalidMediaTypeException e) {
            if (Objects.requireNonNull(file.getFilename()).endsWith(".mp4")) {
                mediaType = MediaType.parseMediaType("video/mp4");
            } else {
                mediaType = MediaType.APPLICATION_OCTET_STREAM;
            }
        }

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.contentLength())
                .contentType(mediaType)
                .body(new InputStreamResource(file.getInputStream()));
    }
}
