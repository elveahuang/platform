package cn.elvea.platform.commons.core.autoconfigure.extensions.swagger.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(SwaggerProperties.PREFIX)
public class SwaggerProperties {

    public static final String PREFIX = "platform.swagger";

    /**
     * 是否开启
     */
    private Boolean enabled = Boolean.TRUE;

    /**
     * 标题
     **/
    private String title = "";

    /**
     * 描述
     **/
    private String description = "";

    /**
     * 版本
     **/
    private String version = "";
}
