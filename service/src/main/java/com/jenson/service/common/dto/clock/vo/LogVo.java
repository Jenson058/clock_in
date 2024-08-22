package com.jenson.service.common.dto.clock.vo;
import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.Entity;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class LogVo{
    private Integer id;
    private Integer year;
    private Integer mouth;
    private Integer day;
    private Integer userId;
    private String userName;
}
