package cn.elvea.platform.ds.autoconfigure;

import cn.elvea.platform.ds.aspect.MasterDataSourceAspect;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.driver.jdbc.core.datasource.ShardingSphereDataSource;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.ReplicaQueryRuleConfiguration;
import org.apache.shardingsphere.replicaquery.api.config.rule.ReplicaQueryDataSourceRuleConfiguration;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

import static cn.elvea.platform.base.constants.DataSourceConstants.*;

/**
 * CustomDataSourceAutoConfiguration
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
@EnableConfigurationProperties({CustomDataSourceConfigProperties.class, DataSourceProperties.class})
public class CustomDataSourceAutoConfiguration {

    public CustomDataSourceAutoConfiguration(CustomDataSourceConfigProperties customDataSourceProperties) {
        if (customDataSourceProperties.isMasterSlaveEnabled()) {
            log.debug("Replica Query - {}", customDataSourceProperties.isMasterSlaveEnabled());
        }
    }

    /**
     * 启用读写分离，初始化主库和从库
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(ShardingSphereDataSource.class)
    @ConditionalOnMissingBean(DataSource.class)
    @ConditionalOnProperty(name = "platform.datasource.master-slave-enabled", havingValue = "true")
    static class ShardingSphereConfiguration {

        /**
         * 读写分离拦截
         *
         * @return {@link MasterDataSourceAspect}
         */
        @Bean
        @ConditionalOnMissingBean(MasterDataSourceAspect.class)
        public MasterDataSourceAspect masterDataSourceAspect() {
            return new MasterDataSourceAspect();
        }

        /**
         * 主库数据源
         */
        @Bean(MASTER_DATASOURCE)
        @ConfigurationProperties(prefix = "spring.datasource.master")
        public DataSource masterDataSource() {
            return DataSourceBuilder.create().build();
        }

        /**
         * 从库数据源
         */
        @Bean(SLAVE_DATASOURCE)
        @ConfigurationProperties(prefix = "spring.datasource.slave")
        public DataSource slaveDataSource() {
            return DataSourceBuilder.create().build();
        }

        @Bean
        @Primary
        public DataSource dataSource(@Qualifier(SLAVE_DATASOURCE) DataSource masterDataSource,
                                     @Qualifier(MASTER_DATASOURCE) DataSource slaveDataSource) throws SQLException {

            Map<String, DataSource> dataSourceMap = new HashMap<>();
            dataSourceMap.put(MASTER_DATASOURCE, masterDataSource);
            dataSourceMap.put(SLAVE_DATASOURCE, slaveDataSource);

            List<ReplicaQueryDataSourceRuleConfiguration> configurations = new ArrayList<>();
            configurations.add(new ReplicaQueryDataSourceRuleConfiguration(MASTER_SLAVE_DATASOURCE, MASTER_DATASOURCE, List.of(SLAVE_DATASOURCE), LOAD_BALANCER));

            Map<String, ShardingSphereAlgorithmConfiguration> loadBalancers = new HashMap<>();
            loadBalancers.put(LOAD_BALANCER, new ShardingSphereAlgorithmConfiguration("ROUND_ROBIN", new Properties()));

            ReplicaQueryRuleConfiguration ruleConfiguration = new ReplicaQueryRuleConfiguration(configurations, loadBalancers);

            Properties properties = new Properties();
            properties.setProperty("sql-show", "true");
            properties.setProperty("sql-simple", "true");

            return ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, List.of(ruleConfiguration), properties);
        }
    }

    /**
     * 禁用读写分离，只初始化主库
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnClass(HikariDataSource.class)
    @ConditionalOnProperty(name = "platform.datasource.master-slave-enabled", havingValue = "false", matchIfMissing = true)
    static class HikariCPConfiguration {

        /**
         * 数据源
         */
        @Primary
        @Bean
        @ConfigurationProperties(prefix = "spring.datasource")
        public DataSource dataSource() {
            return DataSourceBuilder.create().build();
        }

    }

}
