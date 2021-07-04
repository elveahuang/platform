package cn.elvea.platform.core.socket.service;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;

/**
 * WebSocketSessionService
 *
 * @author elvea
 * @since 0.0.1
 */
public interface WebSocketSessionService {

    /**
     * 握手成功后，注册会话
     */
    void createWebSocketSession(WebSocketSession webSocketSession);

    /**
     * 注销会话
     */
    void closeWebSocketSession(WebSocketSession webSocketSession, CloseStatus closeStatus);

    /**
     * 获取当前应用节点单个用户会话记录的所有WebSocket会话记录，注意只是当前节点的会话记录，不包含其他集群节点的会话记录。
     */
    Map<String, WebSocketSession> getWebSocketSession(String userSessionId);

    /**
     * 获取当前应用节点的所有WebSocket会话记录。
     */
    Map<String, Map<String, WebSocketSession>> getWebSocketSession();

}
