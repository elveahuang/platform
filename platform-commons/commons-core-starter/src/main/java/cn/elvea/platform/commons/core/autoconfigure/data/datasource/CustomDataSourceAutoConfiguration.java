package cn.elvea.platform.commons.core.autoconfigure.data.datasource;

import cn.elvea.platform.commons.core.annotations.ds.*;
import cn.elvea.platform.commons.core.autoconfigure.data.datasource.properties.CustomDataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import static cn.elvea.platform.commons.core.autoconfigure.data.datasource.properties.CustomDataSourceProperties.*;
import static cn.elvea.platform.commons.core.constants.DataSourceConstants.*;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@AutoConfiguration(before = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ConditionalOnProperty(prefix = CustomDataSourceProperties.PREFIX, name = "enabled", havingValue = "true")
@EnableConfigurationProperties(CustomDataSourceProperties.class)
public class CustomDataSourceAutoConfiguration {

    public CustomDataSourceAutoConfiguration(CustomDataSourceProperties customDataSourceProperties) {
        log.info("CustomDataSourceAutoConfiguration is enabled.");
        if (customDataSourceProperties.isMasterSlaveEnabled()) {
            log.info("CustomDataSourceAutoConfiguration master-slave is enabled.");
        } else {
            log.info("CustomDataSourceAutoConfiguration master-slave is disabled.");
        }
    }

    /**
     * ========================================================================================================================
     * 默认数据源配置
     * ========================================================================================================================
     */

    @Bean
    @Primary
    public DataSource dataSource(@Autowired(required = false) @Qualifier(MASTER_DATASOURCE) DataSource masterDataSource,
                                 @Autowired(required = false) @Qualifier(SLAVE_DATASOURCE) DataSource slaveDataSource) {
        log.info("Creating PrimaryDataSource...");
        return masterDataSource;
    }

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        log.info("Creating PrimaryTransactionManager...");
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * ========================================================================================================================
     * 主库数据源配置
     * ========================================================================================================================
     */

    @MasterDataSourceProperties
    @Bean(name = MASTER_DATASOURCE_PROPERTIES)
    @ConfigurationProperties("spring.datasource.master")
    @ConditionalOnProperty(prefix = MASTER_DATASOURCE_PREFIX, name = "enabled", havingValue = "true")
    public DataSourceProperties masterDataSourceProperties() {
        log.info("Creating MasterDataSourceProperties...");
        return new DataSourceProperties();
    }

    @MasterDataSource
    @Bean(name = MASTER_DATASOURCE)
    @ConfigurationProperties("spring.datasource.master.hikari")
    @ConditionalOnProperty(prefix = MASTER_DATASOURCE_PREFIX, name = "enabled", havingValue = "true")
    public DataSource masterDataSource(@Qualifier(MASTER_DATASOURCE_PROPERTIES) DataSourceProperties properties) {
        log.info("Creating MasterDataSource...");
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    /**
     * ========================================================================================================================
     * 从库数据源配置
     * ========================================================================================================================
     */

    @SlaveDataSourceProperties
    @Bean(name = SLAVE_DATASOURCE_PROPERTIES)
    @ConfigurationProperties("spring.datasource.slave")
    @ConditionalOnProperty(prefix = SLAVE_DATASOURCE_PREFIX, name = "enabled", havingValue = "true")
    public DataSourceProperties slaveDataSourceProperties() {
        log.info("Creating SlaveDataSourceProperties...");
        return new DataSourceProperties();
    }

    @SlaveDataSource
    @Bean(name = SLAVE_DATASOURCE)
    @ConfigurationProperties("spring.datasource.slave.hikari")
    @ConditionalOnProperty(prefix = SLAVE_DATASOURCE_PREFIX, name = "enabled", havingValue = "true")
    public DataSource slaveDataSource(@Qualifier(SLAVE_DATASOURCE_PROPERTIES) DataSourceProperties properties) {
        log.info("Creating SlaveDataSource...");
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    /**
     * ========================================================================================================================
     * 任务数据源配置
     * ========================================================================================================================
     */

    @JobDataSourceProperties
    @Bean(name = JOB_DATASOURCE_PROPERTIES)
    @ConfigurationProperties("spring.datasource.job")
    @ConditionalOnProperty(prefix = JOB_DATASOURCE_PREFIX, name = "enabled", havingValue = "true")
    public DataSourceProperties jobDataSourceProperties() {
        log.info("Creating JobDataSourceProperties...");
        return new DataSourceProperties();
    }

    @JobDataSource
    @Bean(name = JOB_DATASOURCE)
    @ConfigurationProperties("spring.datasource.job.hikari")
    @ConditionalOnProperty(prefix = JOB_DATASOURCE_PREFIX, name = "enabled", havingValue = "true")
    public DataSource jobDataSource(@Qualifier(JOB_DATASOURCE_PROPERTIES) DataSourceProperties properties) {
        log.info("Creating JobDataSource...");
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @JobTransactionManager
    @Bean(name = JOB_TRANSACTION_MANAGER)
    @ConditionalOnProperty(prefix = JOB_DATASOURCE_PREFIX, name = "enabled", havingValue = "true")
    public PlatformTransactionManager jobTransactionManager(@Qualifier(JOB_DATASOURCE) DataSource dataSource) {
        log.info("Creating JobTransactionManager...");
        DataSourceTransactionManager txManager = new DataSourceTransactionManager();
        txManager.setDataSource(dataSource);
        return txManager;
    }

}
