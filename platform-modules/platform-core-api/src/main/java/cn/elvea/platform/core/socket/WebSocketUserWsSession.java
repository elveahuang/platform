package cn.elvea.platform.core.socket;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * WebSocketUserWsSession
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
public class WebSocketUserWsSession implements Serializable {

    private String webSocketSessionId;

    public WebSocketUserWsSession(String webSocketSessionId) {
        this.webSocketSessionId = webSocketSessionId;
    }

}
