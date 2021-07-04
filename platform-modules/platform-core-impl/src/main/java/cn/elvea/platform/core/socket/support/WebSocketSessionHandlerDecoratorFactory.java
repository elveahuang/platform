package cn.elvea.platform.core.socket.support;

import cn.elvea.platform.core.socket.service.WebSocketSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

/**
 * WebSocketSessionHandlerDecoratorFactory
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Component
public class WebSocketSessionHandlerDecoratorFactory implements WebSocketHandlerDecoratorFactory {

    private final WebSocketSessionService webSocketSessionService;

    public WebSocketSessionHandlerDecoratorFactory(WebSocketSessionService webSocketSessionService) {
        this.webSocketSessionService = webSocketSessionService;
    }

    @Override
    public WebSocketHandler decorate(WebSocketHandler handler) {
        return new WebSocketHandlerDecorator(handler) {
            @Override
            public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
                webSocketSessionService.createWebSocketSession(webSocketSession);
                super.afterConnectionEstablished(webSocketSession);
            }

            @Override
            public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
                webSocketSessionService.closeWebSocketSession(webSocketSession, closeStatus);
                super.afterConnectionClosed(webSocketSession, closeStatus);
            }
        };
    }

}
