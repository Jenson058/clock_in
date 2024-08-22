package com.jenson.service.common.dto.clock.searchBo;
import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.Entity;
import com.jenson.service.common.basic.SuperSearchBo;
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class LogSearchBo extends SuperSearchBo {
    private Integer userId;
    private Integer mouth;
    private Integer year;
}
