package org.lxf.app.common.exception;

import lombok.Getter;
import org.lxf.app.common.enumcase.ResponseCodeEnum;

/**
 * 业务异常
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/6 16:31
 */
@Getter
public class BusinessException extends RuntimeException {
    private final Integer code;

    public BusinessException() {
        this(ResponseCodeEnum.BAD_REQUEST.getCode(), ResponseCodeEnum.BAD_REQUEST.getMessage());
    }

    public BusinessException(String message) {
        this(ResponseCodeEnum.BAD_REQUEST.getCode(), message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.code = ResponseCodeEnum.BAD_REQUEST.getCode();
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public BusinessException(ResponseCodeEnum responseCodeEnum, Object... args) {
        this(responseCodeEnum.getCode(), responseCodeEnum.getMessage().formatted(args));
    }

    public BusinessException(ResponseCodeEnum responseCodeEnum, Throwable cause, Object... args) {
        super(responseCodeEnum.getMessage().formatted(args), cause);
        this.code = responseCodeEnum.getCode();
    }

    public BusinessException(Throwable cause) {
        super(ResponseCodeEnum.BAD_REQUEST.getMessage(), cause);
        this.code = ResponseCodeEnum.BAD_REQUEST.getCode();
    }
}
