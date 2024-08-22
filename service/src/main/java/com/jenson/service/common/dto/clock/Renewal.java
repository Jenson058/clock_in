package com.jenson.service.common.dto.clock;
import com.jenson.service.common.basic.SuperEntity;
import lombok.*;
import lombok.experimental.Accessors;
import javax.persistence.Entity;
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity
public class Renewal extends SuperEntity {

    private Integer fileId;
    private String version;

}
