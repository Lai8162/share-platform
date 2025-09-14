package org.lxf.app.common.util;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 对象序列化工具
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/11 23:19
 */
@Slf4j
public class JacksonObjectUtil {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        // 忽略未知的字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 读取不认识的枚举时，当null值处理
        objectMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
        // 全部输出
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
    }

    /**
     * 将对象序列化成字节流
     *
     * @param value 待转换对象
     * @return 字节数组
     */
    public static byte[] objToByte(Object value) {
        try {
            if (Objects.nonNull(value)) {
                return objectMapper.writeValueAsBytes(value);
            }
        } catch (Exception e) {
            log.warn("将对象序列化成字节失败", e);
        }
        return null;
    }

    /**
     * 将对象序列化成json字符串
     *
     * @param value 待转换字节数组
     * @return Json字符串
     */
    public static String objToJson(Object value) {
        try {
            if (Objects.nonNull(value)) {
                return objectMapper.writeValueAsString(value);
            }
        } catch (Exception e) {
            log.warn("将对象序列化成json字符串失败", e);
        }
        return "";
    }

    /**
     * 将json字符串转对象，支持指定类
     *
     * @param value 待转换字节数组
     * @return 转换完成的实体对象
     */
    public static <T> T jsonToObj(String value, Class<T> classType) {
        try {
            if (StringUtils.isNotBlank(value)) {
                return objectMapper.readValue(value, classType);
            }
        } catch (Exception e) {
            log.warn("将json字符串转对象失败，字符串：{}", value, e);
        }
        return null;
    }


    /**
     * 将json字符串转对象，支持范型类
     *
     * @param value 待转换字节数组
     * @return 转换完成的实体对象
     */
    public static <T> T jsonToObj(String value, TypeReference<T> referenceType) {
        try {
            if (StringUtils.isNotBlank(value)) {
                return objectMapper.readValue(value, referenceType);
            }
        } catch (Exception e) {
            log.warn("将json字符串转对象失败，字符串：{}", value, e);
        }
        return null;
    }

    /**
     * 将字节流转对象，支持范型类
     *
     * @param value 待转换字节数组
     * @return 转换完成的实体对象
     */
    public static <T> T byteToObj(byte[] value, TypeReference<T> referenceType) {
        try {
            if (Objects.nonNull(value)) {
                return objectMapper.readValue(value, referenceType);
            }
        } catch (Exception e) {
            log.warn("将字节流转对象失败", e);
        }
        return null;
    }

    /**
     * 将字节流转成字符串
     *
     * @param bytes 字节数组
     * @return 转换完成的字符串
     */
    public static String byteToString(byte[] bytes) {
        if (Objects.nonNull(bytes)) {
            return new String(bytes, StandardCharsets.UTF_8);
        }
        return StringUtils.EMPTY;
    }
}
