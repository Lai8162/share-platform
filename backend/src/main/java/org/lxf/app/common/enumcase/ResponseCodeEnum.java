package org.lxf.app.common.enumcase;

import lombok.Getter;

/**
 * 返回码枚举
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/5 23:31
 */
@Getter
public enum ResponseCodeEnum {
    OK(200, "Success"),

    // ===== 4xx Client Errors =====
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    CONFLICT(409, "Conflict"),
    TOO_MANY_REQUESTS(429, "Too Many Requests"),

    // ===== 5xx Server Errors =====
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),

    // ===== 自定义 Errors =====
    DATA_NOT_EXIST(2001, "记录不存在"),
    FILTER_CONDITION_NOT_FOUND(2002, "字段%s的条件%s不存在"),
    FILTER_NOT_FOUND(2002, "字段%s不存在"),
    FILTER_LIST(2003, "字段%s的值是列表，请传输正确的值"),
    FILTER_BETWEEN(2004, "字段%s需提供2个边界值"),
    DATA_TO_MANY(2005, "允许最大操作记录数%d条，现操作记录数%d条"),
    DATA_CHECK(2006, "允许操作的记录数%d条，现操作记录数%d条"),
    STATISTICS_TYPE_NOT_FIND(2007, "聚合类型%s不存在"),
    LEVEL_ERROR(2008, "层级处理出现错误"),
    ;

    private final Integer code;
    private final String message;

    ResponseCodeEnum(int code, String message) {
        this.code  = code;
        this.message  = message;
    }

    /**
     * 根据错误码获取枚举实例
     * @param code 错误码
     * @return 对应的ErrorCode枚举
     */
    public static ResponseCodeEnum getByCode(int code) {
        for (ResponseCodeEnum errorCode : ResponseCodeEnum.values())  {
            if (errorCode.getCode()  == code) {
                return errorCode;
            }
        }
        return null;
    }
}
