package com.jenson.service.common.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Objects;

@Accessors
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperSearchBo {

    private Integer id;
    private List<Integer> idList;
    private Integer page = 0;
    private Integer limit = 50;

    public PageRequest getPageRequest() {
        return PageRequest.of(Math.max(0, this.getPage() - 1), limit);
    }

}
