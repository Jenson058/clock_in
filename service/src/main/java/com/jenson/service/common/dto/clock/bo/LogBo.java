package com.jenson.service.common.dto.clock.bo;
import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.Entity;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class LogBo{
    private Integer id;
    private Integer userId;
}
