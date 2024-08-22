package com.jenson.service.common.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BusinessException extends SuperException{

    public static BusinessException err(){
        return (BusinessException) new BusinessException().setCode(500);
    }

    public static BusinessException err(Integer code){
        return (BusinessException) new BusinessException().setCode(code);
    }

    public static BusinessException err(String msg){
        return (BusinessException) new BusinessException().setCode(500).setMsg(msg);
    }

    public static BusinessException err(Integer code,String msg){
        return (BusinessException) new BusinessException().setCode(code).setMsg(msg);
    }
}
