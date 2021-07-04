package cn.elvea.platform.core.socket;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocketUserSession
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
public class WebSocketUserSession implements Serializable {
    /**
     * 用户ID
     */
    private Long uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户会话ID
     */
    private String userSessionId;
    /**
     * Servlet会话ID
     */
    private String servletSessionId;
    /**
     * 当前用户会话记录所有的WebSocket会话链接
     */
    private Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();

    @Data
    public static class WebSocketSession implements Serializable {

        private String id;

        public WebSocketSession(String id) {
            this.id = id;
        }

    }

}
