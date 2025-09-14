package org.lxf.app.common.util;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * Spring Boot上下文请求工具类
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/11 23:12
 */
@Component
public class SpringContextUtil implements ApplicationContextAware, EmbeddedValueResolverAware {
    private static ApplicationContext applicationContext = null;

    private static StringValueResolver stringValueResolver = null;
    
    @Override
    public void setApplicationContext(@org.jetbrains.annotations.NotNull @NotNull ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    @Override
    public void setEmbeddedValueResolver(@org.jetbrains.annotations.NotNull @NotNull StringValueResolver resolver) {
        SpringContextUtil.stringValueResolver = resolver;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * getBean by name.
     *
     * @param name bean name
     * @return {@link Object }
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * getBean by class.
     *
     * @param clazz bean class
     * @return {@link T }
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * getBean by name and class.
     *
     * @param name  bean name
     * @param clazz bean class
     * @return {@link T }
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * getPropertiesString by key.
     *
     * @param key properties key
     * @return {@link String }
     */
    public static String getPropertiesString(String key) {
        String value;
        if ((value = stringValueResolver.resolveStringValue("${" + key + "}")) != null) {
            return value;
        }
        return "";
    }
}
