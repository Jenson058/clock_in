package com.jenson.service.common.dto.file;

import com.jenson.service.common.basic.SuperEntity;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Accessors(chain = true)
@Builder
@EqualsAndHashCode(callSuper = true)
public class File extends SuperEntity {
    // 上传的文件名
    private String oldFileName;
    // MD 加密文件名
    private String mdKey;
    // 文件类型
    private String type;
    // 文件后缀
    private String suffix;
    // 文件地址
    private String filePath;
    private Boolean finish;
}
