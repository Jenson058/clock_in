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
public class Sharding extends SuperEntity {
    private Integer fileId;
    // 文件MD加密名，用于查找相同文件
    private String mdKey;
    // 分片位数
    private Integer number;
    // 分片个数1
    private Integer count;
    // 分片地址
    private String filePath;

}
