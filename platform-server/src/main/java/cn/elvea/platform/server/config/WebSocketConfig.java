package cn.elvea.platform.server.config;

import cn.elvea.platform.core.socket.handler.MessageWebSocketHandler;
import cn.elvea.platform.core.socket.interceptor.WebSocketHandshakeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocketConfig
 *
 * @author elvea
 * @since 0.0.1
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final MessageWebSocketHandler messageWebSocketHandler;

    private final WebSocketHandshakeInterceptor sessionHandshakeInterceptor;

    public WebSocketConfig(MessageWebSocketHandler messageWebSocketHandler, WebSocketHandshakeInterceptor sessionHandshakeInterceptor) {
        this.messageWebSocketHandler = messageWebSocketHandler;
        this.sessionHandshakeInterceptor = sessionHandshakeInterceptor;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(messageWebSocketHandler, "/socket/message")
                .setAllowedOrigins("*")
                .addInterceptors(sessionHandshakeInterceptor)
                .withSockJS();
    }

}
