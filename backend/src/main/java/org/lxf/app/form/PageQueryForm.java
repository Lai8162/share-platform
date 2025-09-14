package org.lxf.app.form;

import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.Map;

/**
 * 统一查询表单
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/6 16:31
 */
@Data
public class PageQueryForm {
    @Min(value = 1, message = "currentPage不合法，必须为整数")
    private Integer currentPage = 1;

    @Min(value = 1, message = "pageSize不合法,必须为整数")
    private Integer pageSize = 10;

    private String orderField;

    private Map<String, Object> filters;

    @Range(min = 0, max = 2, message = "level不合法，必须为0、1或2")
    private Integer level = 1;
}
