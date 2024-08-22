package com.jenson.service.common.exception;

public class PasswordNotPassException extends SuperException {



    public static PasswordNotPassException err() {
        return (PasswordNotPassException) new PasswordNotPassException().setCode(501);
    }

    public static PasswordNotPassException err(Integer code) {
        return (PasswordNotPassException) new PasswordNotPassException().setCode(code);
    }

    public static PasswordNotPassException err(String msg) {
        return (PasswordNotPassException) new PasswordNotPassException().setCode(501).setMsg(msg);
    }

    public static PasswordNotPassException err(Integer code, String msg) {
        return (PasswordNotPassException) new PasswordNotPassException().setCode(code).setMsg(msg);
    }
}
