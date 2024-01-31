package cn.elvea.platform.commons.core.autoconfigure.message.socket;

import cn.elvea.platform.commons.core.autoconfigure.message.socket.properties.WebSocketProperties;
import cn.elvea.platform.commons.core.message.socket.WebSocketSessionHandlerDecoratorFactory;
import cn.elvea.platform.commons.core.message.socket.handler.MessageWebSocketHandler;
import cn.elvea.platform.commons.core.message.socket.message.SocketMessageDelegate;
import cn.elvea.platform.commons.core.message.socket.message.SocketMessageListener;
import cn.elvea.platform.commons.core.message.socket.server.SessionHandshakeInterceptor;
import cn.elvea.platform.commons.core.message.socket.service.DefaultWebSocketService;
import cn.elvea.platform.commons.core.message.socket.service.DefaultWebSocketSessionService;
import cn.elvea.platform.commons.core.message.socket.service.WebSocketService;
import cn.elvea.platform.commons.core.message.socket.service.WebSocketSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * @author elvea
 * @since 24.1.0
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({WebSocketProperties.class})
@ConditionalOnProperty(prefix = WebSocketProperties.PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
public class WebSocketCommonAutoConfiguration {

    public WebSocketCommonAutoConfiguration(WebSocketProperties properties) {
        log.info("WebSocketCommonAutoConfiguration is enabled.");
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketSessionHandlerDecoratorFactory webSocketSessionHandlerDecoratorFactory() {
        return new WebSocketSessionHandlerDecoratorFactory();
    }

    @Bean
    @ConditionalOnMissingBean
    public MessageWebSocketHandler messageWebSocketHandler() {
        return new MessageWebSocketHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public SessionHandshakeInterceptor sessionHandshakeInterceptor() {
        return new SessionHandshakeInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public SocketMessageDelegate socketMessageDelegate() {
        return new SocketMessageDelegate();
    }

    @Bean
    @ConditionalOnMissingBean
    public SocketMessageListener socketMessageListener() {
        return new SocketMessageListener();
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketSessionService webSocketSessionService() {
        return new DefaultWebSocketSessionService();
    }

    @Bean
    @ConditionalOnMissingBean
    public WebSocketService webSocketService(SimpMessagingTemplate messagingTemplate,
                                             WebSocketSessionService webSocketSessionService) {
        return new DefaultWebSocketService(messagingTemplate, webSocketSessionService);
    }

}
