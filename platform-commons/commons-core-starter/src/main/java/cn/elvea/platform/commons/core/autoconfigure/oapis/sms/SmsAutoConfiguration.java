package cn.elvea.platform.commons.core.autoconfigure.oapis.sms;

import cn.elvea.platform.commons.core.autoconfigure.oapis.sms.properties.SmsProperties;
import cn.elvea.platform.commons.core.oapis.sms.SmsSender;
import cn.elvea.platform.commons.core.oapis.sms.SmsServer;
import cn.elvea.platform.commons.core.oapis.sms.aliyun.AliyunSmsSender;
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
@ConditionalOnProperty(prefix = SmsProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties({SmsProperties.class})
public class SmsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public SmsSender smsSender(SmsProperties properties) {
        if (properties.getAliyun().isEnabled()) {
            return new AliyunSmsSender(SmsServer.builder().aliyun(properties.getAliyun()).build());
        }
        return new AliyunSmsSender();
    }

}
