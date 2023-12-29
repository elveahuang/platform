package cn.elvea.platform.commons.core.annotations;

import cn.elvea.platform.commons.core.enums.RateLimitTypeEnum;

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

    RateLimitTypeEnum type() default RateLimitTypeEnum.DEFAULT;

    String message() default "{rate.limiter.message}";

}
