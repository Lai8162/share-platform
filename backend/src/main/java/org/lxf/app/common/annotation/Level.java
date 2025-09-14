package org.lxf.app.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 层级注解
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/9 2:56
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Level {
    int value() default 1;
}
