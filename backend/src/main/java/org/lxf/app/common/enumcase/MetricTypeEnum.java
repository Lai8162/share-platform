package org.lxf.app.common.enumcase;

/**
 * 统计指标类型枚举
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/9 0:21
 */
public enum MetricTypeEnum {
    SUM("sum"),
    COUNT("count"),
    AVG("avg"),
    MAX("max"),
    MIN("min"),
    ;

    private final String type;

    MetricTypeEnum(String type) {
        this.type = type;
    }

    public static MetricTypeEnum ofType(String type) {
        for (MetricTypeEnum value : MetricTypeEnum.values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return null;
    }

    public static boolean isValid(String type) {
        for (MetricTypeEnum value : MetricTypeEnum.values()) {
            if (value.type.equals(type)) {
                return true;
            }
        }
        return false;
    }
}
