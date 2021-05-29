package cn.elvea.platform.persistence.autoconfigure;

import cn.elvea.platform.commons.Context;
import cn.elvea.platform.commons.id.IdWorker;
import cn.elvea.platform.persistence.jdbc.auditor.UserAuditorAware;
import cn.elvea.platform.persistence.jdbc.callback.IdEntityCallback;
import cn.elvea.platform.persistence.jdbc.converter.BooleanToIntegerConverter;
import cn.elvea.platform.persistence.jdbc.converter.IntegerToBooleanConverter;
import cn.elvea.platform.persistence.jdbc.dao.CommonDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Custom JDBC Auto Configuration
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration
@EnableJdbcAuditing
public class CustomJdbcAutoConfiguration extends AbstractJdbcConfiguration {

    /**
     * NamedParameterJdbcOperations
     *
     * @param dataSource {@link DataSource}
     * @return {@link NamedParameterJdbcOperations}
     */
    @Bean
    @ConditionalOnMissingBean
    public NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * JdbcCustomConversions
     *
     * @return {@link JdbcCustomConversions}
     */
    @Bean
    @Override
    public JdbcCustomConversions jdbcCustomConversions() {
        return new JdbcCustomConversions(Arrays.asList(
                new IntegerToBooleanConverter(),
                new BooleanToIntegerConverter())
        );
    }

    /**
     * AuditorAware<Long>
     *
     * @return {@link Context}
     */
    @Bean
    public AuditorAware<Long> auditorAware(Context context) {
        return new UserAuditorAware(context);
    }

    /**
     * IdEntityCallback
     *
     * @return {@link IdEntityCallback}
     */
    @Bean
    @ConditionalOnMissingBean
    public IdEntityCallback idEntityCallback(IdWorker idWorker) {
        Assert.notNull(idWorker, "idWorker must not be null!");
        return new IdEntityCallback(idWorker);
    }

    /**
     * CommonDao
     *
     * @return {@link CommonDao}
     */
    @Bean
    @ConditionalOnMissingBean
    public CommonDao commonDao(JdbcTemplate jdbcTemplate) {
        Assert.notNull(jdbcTemplate, "JdbcTemplate must not be null!");
        return new CommonDao(jdbcTemplate);
    }

}
