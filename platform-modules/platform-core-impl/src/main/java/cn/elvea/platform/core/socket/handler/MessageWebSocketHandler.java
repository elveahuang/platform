package cn.elvea.platform.core.socket.handler;

import cn.elvea.platform.core.socket.service.WebSocketSessionService;
import cn.elvea.platform.core.socket.support.AbstractWebSocketSessionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.Date;

/**
 * MessageWebSocketHandler
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Component
public class MessageWebSocketHandler extends AbstractWebSocketSessionHandler {

    public MessageWebSocketHandler(WebSocketSessionService webSocketSessionService) {
        super(webSocketSessionService);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.debug("sending message......");
        session.sendMessage(new TextMessage(new Date().toString()));
    }

}
