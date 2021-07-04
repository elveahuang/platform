package cn.elvea.platform.core.socket.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * WebSocketEventListener
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Component
public class WebSocketConnectionListener implements ApplicationListener<SessionConnectEvent> {

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String webSocketSessionId = accessor.getSessionId();
        log.debug("web socket connect event - {} - {}", webSocketSessionId, accessor.getCommand());
    }

    @EventListener
    public void onSocketConnected(SessionConnectedEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String webSocketSessionId = accessor.getSessionId();
        log.debug("web socket connected - {} - {}", webSocketSessionId, accessor.getCommand());
    }

    @EventListener
    public void onSocketDisconnected(SessionDisconnectEvent event) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        String webSocketSessionId = accessor.getSessionId();
        log.debug("web socket disconnected - {} - {}", webSocketSessionId, accessor.getCommand());
    }

}
