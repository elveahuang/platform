package cn.elvea.platform.commons.autoconfigure.jwt;

import cn.elvea.platform.commons.autoconfigure.jwt.JwtConfigProperties;
import cn.elvea.platform.commons.jwt.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JwtAutoConfiguration
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({JwtConfigProperties.class})
public class JwtAutoConfiguration {

    /**
     * JwtService
     *
     * @return {@link JwtService}
     */
    @Bean
    @ConditionalOnMissingBean
    public JwtService jwtService(JwtConfigProperties config) {
        return new JwtService(config);
    }

}
