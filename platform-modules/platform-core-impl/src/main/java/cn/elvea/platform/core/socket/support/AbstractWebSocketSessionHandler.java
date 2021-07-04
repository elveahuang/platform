package cn.elvea.platform.core.socket.support;

import cn.elvea.platform.core.socket.service.WebSocketSessionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * WebSocketSessionHandlerDecoratorFactory
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public abstract class AbstractWebSocketSessionHandler extends TextWebSocketHandler {

    private final WebSocketSessionService webSocketSessionService;

    public AbstractWebSocketSessionHandler(WebSocketSessionService webSocketSessionService) {
        this.webSocketSessionService = webSocketSessionService;
    }

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

}
