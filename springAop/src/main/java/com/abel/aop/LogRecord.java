package com.abel.aop;

import java.lang.annotation.*;

/**
 * Created by yangyibo on 17/7/9.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogRecord {
    String key() default "";
}
