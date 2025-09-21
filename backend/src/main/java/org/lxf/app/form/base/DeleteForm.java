package org.lxf.app.form.base;

import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.Map;

/**
 * 统一删除表单
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/6 23:35
 */
@Data
public class DeleteForm {
    private Map<String, Object> filters;

    @Min(value = 0, message = "maxCount不合法，必须为整数")
    private Integer maxCount;

    @Min(value = 0, message = "checkCount不合法，必须为整数")
    private Integer checkCount;
}
