package com.jenson.service.common.dto.clock;
import com.jenson.service.common.basic.SuperEntity;
import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.Entity;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Log extends SuperEntity {
    private Integer year;
    private Integer mouth;
    private Integer day;
    private Integer userId;
}
