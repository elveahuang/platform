package cn.elvea.platform.commons.autoconfigure.id;

import cn.elvea.platform.commons.id.IdWorker;
import cn.elvea.platform.commons.id.IdWorkerManager;
import cn.elvea.platform.commons.id.IdWorkerScheduler;
import cn.elvea.platform.commons.id.impl.IdWork;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.Assert;

/**
 * IdWorkerAutoConfiguration
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({IdConfigProperties.class})
public class IdAutoConfiguration {

    /**
     * 自动配置数据中心ID和工作机器ID
     */
    @EnableScheduling
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnProperty(name = "platform.id.type", havingValue = "auto", matchIfMissing = true)
    static class SnowflakeAutoConfiguration {

        private final IdConfigProperties idWorkerConfigProperties;

        private final RedissonClient redissonClient;

        public SnowflakeAutoConfiguration(IdConfigProperties idWorkerConfigProperties, RedissonClient redissonClient) {
            Assert.notNull(idWorkerConfigProperties, "idWorkerConfigProperties must not be null!");
            Assert.notNull(redissonClient, "redissonClient must not be null!");
            this.idWorkerConfigProperties = idWorkerConfigProperties;
            this.redissonClient = redissonClient;
        }

        /**
         * IdWorkerManager
         *
         * @return {@link IdWorkerManager}
         */
        @Bean
        @ConditionalOnMissingBean
        public IdWorkerManager idWorkerManager() {
            return new IdWorkerManager(this.idWorkerConfigProperties, this.redissonClient);
        }

        /**
         * IdWorker
         *
         * @return {@link IdWorker}
         */
        @Bean
        @ConditionalOnMissingBean
        @DependsOn({"idWorkerManager"})
        public IdWorker idWorker(IdWorkerManager idWorkerManager) throws Exception {
            return idWorkerManager.createIdWorker();
        }

        /**
         * IdWorkerScheduler
         *
         * @return {@link IdWorkerScheduler}
         */
        @Bean
        @ConditionalOnMissingBean
        @DependsOn({"idWorkerManager"})
        public IdWorkerScheduler idWorkerScheduler(IdWorkerManager idWorkerUtils) {
            return new IdWorkerScheduler(idWorkerUtils);
        }

    }

    /**
     * 手工配置数据中心ID和工作机器ID
     */
    @Configuration(proxyBeanMethods = false)
    @ConditionalOnProperty(name = "platform.id.type", havingValue = "manual")
    static class SnowflakeManualConfiguration {

        /**
         * IdWorker
         *
         * @return {@link IdWorker}
         */
        @Bean
        public IdWorker idWorker(IdConfigProperties idWorkerConfigProperties) {
            return new IdWork(idWorkerConfigProperties.getDatacenterId(), idWorkerConfigProperties.getWorkerId());
        }

    }

}
