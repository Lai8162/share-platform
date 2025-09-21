package org.lxf.app.form.base;

import lombok.Data;

import java.util.Map;

/**
 * 统一统计表单
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/8 23:59
 */
@Data
public class StatisticsForm {
    private Map<String, Object> filters;

    private String metrics;

    private String groupField;

    private String orderField;
}
