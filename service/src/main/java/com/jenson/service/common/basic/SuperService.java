package com.jenson.service.common.basic;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

public interface SuperService<Vo,Bo,SearchBo extends SuperSearchBo>{
    Page<Vo> pageAll( SearchBo searchBo);
    List<Vo> listAll( SearchBo searchBo);
    Vo get(Integer id);

    @Transactional
    Vo edit( Bo bo);

    @Transactional
    List<Vo> edit( List<Bo> boList);

    @Transactional
    Vo del( Integer id);

    @Transactional
    List<Vo> del( SearchBo searchBo);
}
