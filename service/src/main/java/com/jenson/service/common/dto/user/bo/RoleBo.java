package com.jenson.service.common.dto.user.bo;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class RoleBo {
    private Integer id;
    private String name;
    private List<String> authAll;
}
