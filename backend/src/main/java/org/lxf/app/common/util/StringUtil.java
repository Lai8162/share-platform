package org.lxf.app.common.util;

/**
 * 字符串工具类
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/12 15:02
 */
public class StringUtil {
    /**
     * 驼峰转下划线格式（例如：employeeNumber -> employee_number）
     *
     * @param lowerCamelStr 小驼峰字符串
     * @return 下划线格式的字符串
     */
    public static String lowerCamelToSnake(String lowerCamelStr) {
        if (lowerCamelStr == null || lowerCamelStr.isEmpty()) {
            return lowerCamelStr;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lowerCamelStr.length(); i++) {
            char currentChar = lowerCamelStr.charAt(i);
            if (Character.isUpperCase(currentChar)) {
                result.append('_').append(Character.toLowerCase(currentChar));
            } else {
                result.append(currentChar);
            }
        }
        return result.toString();
    }

    /**
     * 驼峰转下划线格式
     *
     * @param snakeCaseStr 下划线字符串
     * @return 驼峰格式的字符串
     */
    public static String snakeToLowerCamel(String snakeCaseStr) {
        if (snakeCaseStr == null || snakeCaseStr.isEmpty()) {
            return snakeCaseStr;
        }

        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;

        for (int i = 0; i < snakeCaseStr.length(); i++) {
            char currentChar = snakeCaseStr.charAt(i);
            if (currentChar == '_') {
                nextUpper = true;
            } else {
                if (nextUpper) {
                    result.append(Character.toUpperCase(currentChar));
                    nextUpper = false;
                } else {
                    result.append(Character.toLowerCase(currentChar));
                }
            }
        }
        return result.toString();
    }
}
