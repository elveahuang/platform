package cn.elvea.platform.commons.core.annotations;

import jakarta.annotation.security.PermitAll;

import java.lang.annotation.*;

/**
 * @author dev
 * @since 0.0.1
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@PermitAll
public @interface Anonymous {
}
