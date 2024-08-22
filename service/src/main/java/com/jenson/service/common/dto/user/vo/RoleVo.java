package com.jenson.service.common.dto.user.vo;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Builder
public class RoleVo {
    private Integer id;
    private String name;
    private List<String> authAll;
}
