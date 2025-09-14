package org.lxf.app.form;

import jakarta.validation.constraints.Min;
import lombok.Data;

import java.util.Map;

/**
 * 统一更新表单
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/6 18:48
 */
@Data
public class UpdateForm {
    private Map<String, Object> filters;

    private Map<String, Object> updates;

    @Min(value = 0, message = "maxCount不合法，必须为整数")
    private Integer maxCount;

    @Min(value = 0, message = "checkCount不合法，必须为整数")
    private Integer checkCount;
}
