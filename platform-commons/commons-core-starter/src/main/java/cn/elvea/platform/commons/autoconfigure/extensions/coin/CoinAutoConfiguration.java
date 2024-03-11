package cn.elvea.platform.commons.autoconfigure.extensions.coin;

import cn.elvea.platform.commons.autoconfigure.extensions.coin.properties.CoinProperties;
import cn.elvea.platform.commons.extensions.coin.CoinManager;
import cn.elvea.platform.commons.extensions.coin.DefaultCoinManager;
import cn.elvea.platform.commons.extensions.coin.CoinConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(prefix = CoinProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({CoinProperties.class})
public class CoinAutoConfiguration {

    public CoinAutoConfiguration() {
        log.info("CoinAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public CoinManager coinManager(CoinProperties properties) {
        CoinConfig config = CoinConfig.builder()
                .etherScanApiKey(properties.getEtherscan().getApiKey())
                .build();
        return new DefaultCoinManager(config);
    }

}
