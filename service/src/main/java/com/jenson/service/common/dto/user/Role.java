package com.jenson.service.common.dto.user;

import com.jenson.service.common.basic.SuperEntity;
import com.jenson.service.common.convert.StringListConvert;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.Convert;
import javax.persistence.Entity;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
@EqualsAndHashCode(callSuper = true)
@Entity
public class Role extends SuperEntity {
    private String name;
    @Convert(converter = StringListConvert.class)
    private List<String> authAll;
}
