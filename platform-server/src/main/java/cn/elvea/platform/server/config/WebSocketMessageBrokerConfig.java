package cn.elvea.platform.server.config;

import cn.elvea.platform.core.socket.support.WebSocketSessionHandlerDecoratorFactory;
import cn.elvea.platform.core.socket.interceptor.WebSocketChannelInterceptor;
import cn.elvea.platform.core.socket.interceptor.WebSocketHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

/**
 * WebSocketMessageBrokerConfig
 *
 * @author elvea
 * @since 0.0.1
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {

    private WebSocketSessionHandlerDecoratorFactory sessionHandlerDecoratorFactory;

    private WebSocketHandshakeInterceptor sessionHandshakeInterceptor;

    private WebSocketChannelInterceptor securityChannelInterceptor;

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(this.securityChannelInterceptor);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic", "/user");
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user/");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/web-socket")
                .setAllowedOrigins("*")
                .addInterceptors(this.sessionHandshakeInterceptor);

        registry.addEndpoint("/web-socket/sock-js")
                .setAllowedOrigins("*")
                .addInterceptors(this.sessionHandshakeInterceptor)
                .withSockJS();
    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry.addDecoratorFactory(sessionHandlerDecoratorFactory);
    }

    @Autowired
    public void setSessionHandlerDecoratorFactory(WebSocketSessionHandlerDecoratorFactory sessionHandlerDecoratorFactory) {
        this.sessionHandlerDecoratorFactory = sessionHandlerDecoratorFactory;
    }

    @Autowired
    public void setSessionHandshakeInterceptor(WebSocketHandshakeInterceptor sessionHandshakeInterceptor) {
        this.sessionHandshakeInterceptor = sessionHandshakeInterceptor;
    }

    @Autowired
    public void setSecurityChannelInterceptor(WebSocketChannelInterceptor securityChannelInterceptor) {
        this.securityChannelInterceptor = securityChannelInterceptor;
    }

}
