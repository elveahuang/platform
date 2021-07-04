package cn.elvea.platform.core.socket.service;

import cn.elvea.platform.core.socket.message.SimpleTextMessage;

/**
 * WebSocketService
 *
 * @author elvea
 * @since 0.0.1
 */
public interface WebSocketService {

    void sendSimpleMessage(SimpleTextMessage simpleTextMessage);

    void handleSimpleMessage(SimpleTextMessage simpleTextMessage);

    // =================================================================================================================
    // 会话相关
    // =================================================================================================================

}
