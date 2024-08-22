package com.jenson.service.common.basic;

import lombok.Data;
import lombok.experimental.Accessors;


@Accessors(chain = true)
@Data
public class Result<T> {

    private static final Integer successCode = 200;
    private static final Integer errorCode = 500;

    private static final String successMsg = "调用成功";
    private static final String errorMsg = "调用失败";

    private Integer code;
    private String msg;
    private T content;

    public static <T> Result<T> suc() {
        return new Result<T>().setCode(successCode).setMsg(successMsg);
    }

    public static <T> Result<T> suc(String msg) {
        return new Result<T>().setCode(successCode).setMsg(msg);
    }

    public static <T> Result<T> suc(T t) {
        return new Result<T>().setCode(successCode).setMsg(successMsg).setContent(t);
    }

    public static <T> Result<T> suc(T t, String msg) {
        return new Result<T>().setCode(successCode).setMsg(msg).setContent(t);
    }

    public static <T> Result<T> err() {
        return new Result<T>().setCode(errorCode).setMsg(errorMsg);
    }

    public static <T> Result<T> err(T t) {
        return new Result<T>().setCode(errorCode).setMsg(errorMsg).setContent(t);
    }

    public static <T> Result<T> err(String msg) {
        return new Result<T>().setCode(errorCode).setMsg(msg);
    }

    public static <T> Result<T> err(Integer code, T t) {
        return new Result<T>().setCode(code).setContent(t);
    }

    public static <T> Result<T> err(Integer code, String msg) {
        return new Result<T>().setCode(code).setMsg(msg);
    }

    public static <T> Result<T> err(Integer code, String msg,T t) {
        return new Result<T>().setCode(code).setMsg(msg).setContent(t);
    }
}
