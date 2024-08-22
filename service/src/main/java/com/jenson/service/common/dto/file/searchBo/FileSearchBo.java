package com.jenson.service.common.dto.file.searchBo;

import com.jenson.service.common.basic.SuperSearchBo;
import lombok.*;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@EqualsAndHashCode(callSuper = true)
public class FileSearchBo extends SuperSearchBo {
}
