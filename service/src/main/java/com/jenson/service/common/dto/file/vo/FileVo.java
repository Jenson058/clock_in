package com.jenson.service.common.dto.file.vo;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class FileVo {
    private Integer id;
    private String oldFileName;
    private String mdKey;
    private String type;
    private String suffix;
    private Integer number_;
    private Integer count;
    private String filePath;
    private Boolean finish;
}
