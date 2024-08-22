package com.jenson.service.controller.clock;

import com.jenson.service.common.basic.SuperController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jenson.service.common.dto.clock.bo.RenewalBo;
import com.jenson.service.common.dto.clock.searchBo.RenewalSearchBo;
import com.jenson.service.common.dto.clock.vo.RenewalVo;
import com.jenson.service.service.clock.RenewalService;
@RestController
@RequestMapping("/api/renewal")
public class RenewalController extends SuperController<RenewalVo, RenewalBo, RenewalSearchBo, RenewalService> {

}