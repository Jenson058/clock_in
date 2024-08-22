package com.jenson.service.common.dto.file.bo;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class FileBo {
    private String oldFileName;
    private String mdKey;
    private String type;
    private String suffix;
    private MultipartFile file;
    @NonNull
    private Integer number_;
    @NonNull
    private Integer count;
}
