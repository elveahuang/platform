package cn.elvea.platform.commons.core.autoconfigure.extensions.jwt.properties;

import cn.elvea.platform.commons.core.extensions.jwt.JwtConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(JwtProperties.PREFIX)
public class JwtProperties extends JwtConfig {

    public static final String PREFIX = "platform.jwt";

    private Boolean enabled = Boolean.FALSE;

}
