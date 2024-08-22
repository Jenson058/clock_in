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
public class ToKenException extends SuperException{

    public static ToKenException err() {
        return (ToKenException) new ToKenException().setCode(500);
    }

    public static ToKenException err(Integer code) {
        return (ToKenException) new ToKenException().setCode(code);
    }

    public static ToKenException err(String msg) {
        return (ToKenException) new ToKenException().setCode(500).setMsg(msg);
    }

    public static ToKenException err(Integer code, String msg) {
        return (ToKenException) new ToKenException().setCode(code).setMsg(msg);
    }

}
