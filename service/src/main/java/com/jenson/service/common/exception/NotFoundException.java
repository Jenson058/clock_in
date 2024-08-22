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
public class NotFoundException extends SuperException {

    public static NotFoundException err() {
        return (NotFoundException) new NotFoundException().setCode(500);
    }

    public static NotFoundException err(Integer code) {
        return (NotFoundException) new NotFoundException().setCode(code);
    }

    public static NotFoundException err(String msg) {
        return (NotFoundException) new NotFoundException().setCode(500).setMsg(msg);
    }

    public static NotFoundException err(Integer code, String msg) {
        return (NotFoundException) new NotFoundException().setCode(code).setMsg(msg);
    }

    public static NotFoundException notFound(Integer id, String msg) {
        return (NotFoundException) new NotFoundException().setCode(500).setMsg("未找到id为" + id + "的" + msg);
    }
}
