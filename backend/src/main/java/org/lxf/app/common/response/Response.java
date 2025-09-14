package org.lxf.app.common.response;

import lombok.Data;
import org.lxf.app.common.enumcase.ResponseCodeEnum;

/**
 * 统一返回响应
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/5 23:31
 */
@Data
public class Response<T> {
    private Integer code;
    private String message;
    private T data;

    public Response() {
    }

    public static <T> Response<T> ok() {
        Response<T> response = new Response<>();
        response.setCode(ResponseCodeEnum.OK.getCode());
        response.setMessage(ResponseCodeEnum.OK.getMessage());
        return response;
    }

    public static <T> Response<T> ok(T data) {
        Response<T> response = ok();
        response.setData(data);
        return response;
    }

    public static <T> Response<T> ok(Integer code, T data) {
        Response<T> response = ok();
        response.setCode(code);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> ok(Integer code, String msg) {
        Response<T> response = ok();
        response.setCode(code);
        response.setMessage(msg);
        return response;
    }

    public static <T> Response<T> ok(String msg, T data) {
        Response<T> response = ok();
        response.setMessage(msg);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> ok(Integer code, String msg, T data) {
        Response<T> response = ok();
        response.setCode(code);
        response.setMessage(msg);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> fail() {
        Response<T> response = new Response<>();
        response.setCode(ResponseCodeEnum.BAD_REQUEST.getCode());
        response.setMessage(ResponseCodeEnum.BAD_REQUEST.getMessage());
        return response;
    }

    public static <T> Response<T> fail(T data) {
        Response<T> response = fail();
        response.setData(data);
        return response;
    }

    public static <T> Response<T> fail(Integer code, T data) {
        Response<T> response = fail();
        response.setCode(code);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> fail(Integer code, String msg) {
        Response<T> response = fail();
        response.setCode(code);
        response.setMessage(msg);
        return response;
    }

    public static <T> Response<T> fail(String msg, T data) {
        Response<T> response = fail();
        response.setMessage(msg);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> fail(Integer code, String msg, T data) {
        Response<T> response = fail();
        response.setCode(code);
        response.setMessage(msg);
        response.setData(data);
        return response;
    }

    public static <T> Response<T> forbidden() {
        Response<T> response = fail();
        response.setCode(ResponseCodeEnum.FORBIDDEN.getCode());
        response.setMessage(ResponseCodeEnum.FORBIDDEN.getMessage());
        return response;
    }

    public static <T> Response<T> forbidden(String msg) {
        Response<T> response = forbidden();
        response.setMessage(msg);
        return response;
    }
}
