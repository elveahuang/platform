package cn.elvea.platform.core.socket.service;

import cn.elvea.platform.core.socket.message.SimpleTextMessage;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import static cn.elvea.platform.core.socket.WebSocketConstants.WEB_SOCKET_TOPIC_TEST;

/**
 * WebSocketConstants
 *
 * @author elvea
 * @since 0.0.1
 */
@Service
public class WebSocketServiceImpl implements WebSocketService {

    private final RedissonClient redissonClient;
    private final WebSocketSessionService webSocketSessionService;

    public WebSocketServiceImpl(RedissonClient redissonClient,
                                WebSocketSessionService webSocketSessionService) {
        this.redissonClient = redissonClient;
        this.webSocketSessionService = webSocketSessionService;
    }

    @Override
    public void sendSimpleMessage(SimpleTextMessage simpleTextMessage) {
        RTopic topic = redissonClient.getTopic(WEB_SOCKET_TOPIC_TEST);
        topic.publish(simpleTextMessage);
    }

    @Override
    public void handleSimpleMessage(SimpleTextMessage simpleTextMessage) {
        System.out.println(simpleTextMessage.getContent());
    }

}
