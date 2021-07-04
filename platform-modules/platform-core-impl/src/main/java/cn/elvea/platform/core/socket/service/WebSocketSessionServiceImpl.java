package cn.elvea.platform.core.socket.service;

import cn.elvea.platform.core.socket.WebSocketUserSession;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static cn.elvea.platform.core.socket.WebSocketConstants.WEB_SOCKET_USER_SESSION;
import static cn.elvea.platform.core.socket.WebSocketConstants.WEB_SOCKET_USER_SESSION_REQUEST_KEY;

/**
 * WebSocketSessionService
 *
 * @author elvea
 * @see WebSocketSessionService
 * @since 0.0.1
 */
@Slf4j
@Service
public class WebSocketSessionServiceImpl implements WebSocketSessionService {

    private final RedissonClient redissonClient;

    public WebSocketSessionServiceImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 用于保存当前应用节点的所有会话记录
     */
    private final ConcurrentHashMap<String, Map<String, WebSocketSession>> webSocketSessionMap = new ConcurrentHashMap<>();

    @Override
    public void createWebSocketSession(WebSocketSession webSocketSession) {
        WebSocketUserSession webSocketUserSession = (WebSocketUserSession) webSocketSession.getAttributes().get(WEB_SOCKET_USER_SESSION_REQUEST_KEY);

        if (webSocketUserSession != null) {
            String webSocketSessionId = webSocketSession.getId();
            String userSessionId = webSocketUserSession.getUserSessionId();
            if (log.isDebugEnabled()) {
                log.debug("create socket session [{}] for user session [{}].", webSocketSessionId, userSessionId);
            }

            // 保存当前节点的会话记录
            // 所有WebSocket的操作，都需要经由Redis广播订阅后在各个节点中发送消息到客户端
            Map<String, WebSocketSession> webSocketSessionMap = this.webSocketSessionMap.get(userSessionId);
            if (webSocketSessionMap == null) {
                webSocketSessionMap = new ConcurrentHashMap<>();
                this.webSocketSessionMap.putIfAbsent(userSessionId, webSocketSessionMap);
                webSocketSessionMap = this.webSocketSessionMap.get(userSessionId);
            }
            webSocketSessionMap.put(webSocketSession.getId(), webSocketSession);

            // 处理用户会话记录
            // 这里保存的是全站的用户在线记录跟踪
            RMap<String, WebSocketUserSession> userSessionMap = this.redissonClient.getMap(WEB_SOCKET_USER_SESSION);
            RLock lock = userSessionMap.getLock(webSocketSessionId);
            try {
                lock.lock();
                WebSocketUserSession socketUserSession = userSessionMap.getOrDefault(webSocketSessionId, webSocketUserSession);
                socketUserSession.getSessionMap().putIfAbsent(webSocketSessionId, new WebSocketUserSession.WebSocketSession(webSocketSessionId));
                userSessionMap.putIfAbsent(userSessionId, socketUserSession);
            } finally {
                lock.unlock();
            }
        }
    }

    @Override
    public void closeWebSocketSession(WebSocketSession webSocketSession, CloseStatus closeStatus) {
        WebSocketUserSession webSocketUserSession = (WebSocketUserSession) webSocketSession.getAttributes().get(WEB_SOCKET_USER_SESSION_REQUEST_KEY);

        if (webSocketUserSession != null) {
            String webSocketSessionId = webSocketSession.getId();
            String userSessionId = webSocketUserSession.getUserSessionId();

            // 移除当前节点的会话记录
            Map<String, WebSocketSession> webSocketSessionMap = this.webSocketSessionMap.get(userSessionId);
            if (webSocketSessionMap != null) {
                boolean result = webSocketSessionMap.remove(webSocketSessionId) != null;
                if (log.isDebugEnabled()) {
                    log.debug("remove web socket session [{}][{}] - {}.", userSessionId, webSocketSessionId, result);
                }
                if (webSocketSessionMap.isEmpty()) {
                    this.webSocketSessionMap.remove(userSessionId);
                    if (log.isDebugEnabled()) {
                        log.debug("remove all web socket session [{}].", userSessionId);
                    }
                }
            }

            // 处理用户会话记录
            // 这里保存的是全站的用户在线记录跟踪
            RMap<String, WebSocketUserSession> userSessionMap = this.redissonClient.getMap(WEB_SOCKET_USER_SESSION);
            RLock lock = userSessionMap.getLock(userSessionId);
            try {
                lock.lock();

                WebSocketUserSession userSession = userSessionMap.get(userSessionId);
                if (userSession != null) {
                    boolean result = userSession.getSessionMap().remove(webSocketSessionId) != null;
                    if (log.isDebugEnabled()) {
                        log.debug("close socket session [{}] of user session [{}] was {}", webSocketSessionId, userSessionId, result);
                    }
                    if (userSession.getSessionMap().isEmpty()) {
                        userSessionMap.remove(userSessionId);
                    } else {
                        userSessionMap.putIfAbsent(userSessionId, userSession);
                    }
                }
            } finally {
                lock.unlock();
            }
        }
    }

    @Override
    public Map<String, WebSocketSession> getWebSocketSession(String userSessionId) {
        return this.webSocketSessionMap.get(userSessionId);
    }

    @Override
    public Map<String, Map<String, WebSocketSession>> getWebSocketSession() {
        return this.webSocketSessionMap;
    }

}
