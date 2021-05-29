package cn.elvea.platform.server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

/**
 * Config
 *
 * @author elvea
 * @since 0.0.1
 */
@Configuration
@ComponentScan(basePackages = {
        "cn.elvea.platform.core",
        "cn.elvea.platform.lxp"
})
@EnableJdbcRepositories(basePackages = {
        "cn.elvea.platform.core.**.repository"
})
@MapperScan(basePackages = {
        "cn.elvea.platform.core.**.mapper"
})
public class Config {
}
