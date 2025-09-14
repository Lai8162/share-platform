package org.lxf.app.common.plugin.okhttp;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * OkHttp请求返回实体类对象
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/11 23:01
 */
@Data
@NoArgsConstructor
public class OkHttpClientResult {
    /**
     * 是否成功
     */
    private boolean success = false;

    /**
     * http级，状态标识码
     */
    private Integer code;

    /**
     * http级，错误信息
     */
    private String message;

    /**
     * http级，返回头部
     */
    private Map<String, List<String>> headers;

    /**
     * http级，返回body
     */
    private byte[] body;

    public OkHttpClientResult(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
