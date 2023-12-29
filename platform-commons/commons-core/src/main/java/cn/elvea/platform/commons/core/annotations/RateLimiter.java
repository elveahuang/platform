package cn.elvea.platform.commons.core.annotations;

import cn.elvea.platform.commons.core.enums.RateLimitType;

import java.lang.annotation.*;

/**
 * @author elvea
 * @since 0.0.1
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RateLimiter {

    String key() default "";

    int time() default 60;

    int limit() default 100;

    RateLimitType type() default RateLimitType.DEFAULT;

    String message() default "{rate.limiter.message}";

}
