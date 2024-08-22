package com.jenson.service.common.dto.clock.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class RenewalVo{
    private Integer id;
    private Integer fileId;
    private String version;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date updatedAt;
}
