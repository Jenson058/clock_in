package com.jenson.service.common.basic;

import com.jenson.service.util.SystemUtil;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@Accessors(chain = true)
public class SuperEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean deleted = false;

    private String createdBy;
    private Date createdAt;

    private String updatedBy;
    private Date updatedAt;

    @PrePersist
    private void setCreated(){
        createdAt = new Date();
        updatedAt = new Date();
        createdBy = SystemUtil.getLoginUser();
        updatedBy = SystemUtil.getLoginUser();
    }

    @PreUpdate
    private void setUpdated(){
        updatedAt = new Date();
        updatedBy = SystemUtil.getLoginUser();
    }
}
