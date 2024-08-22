package com.jenson.service.controller.user;

import com.jenson.service.common.basic.SuperController;
import com.jenson.service.common.dto.user.bo.RoleBo;
import com.jenson.service.common.dto.user.searchBo.RoleSearchBo;
import com.jenson.service.common.dto.user.vo.RoleVo;
import com.jenson.service.service.user.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/role")
public class RoleController extends SuperController<RoleVo, RoleBo, RoleSearchBo, RoleService> {

}
