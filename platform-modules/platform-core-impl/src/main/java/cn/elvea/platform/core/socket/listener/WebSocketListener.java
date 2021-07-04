package cn.elvea.platform.core.socket.listener;

import cn.elvea.platform.core.socket.message.SimpleTextMessage;
import cn.elvea.platform.core.socket.service.WebSocketService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import static cn.elvea.platform.core.socket.WebSocketConstants.WEB_SOCKET_TOPIC_TEST;

/**
 * WebSocketListener
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
@Component
public class WebSocketListener implements ApplicationRunner, Ordered {

    private final RedissonClient redissonClient;
    private final WebSocketService webSocketService;

    public WebSocketListener(RedissonClient redissonClient, WebSocketService webSocketService) {
        this.redissonClient = redissonClient;
        this.webSocketService = webSocketService;
    }

    @Override
    public void run(ApplicationArguments args) {
        RTopic topic = redissonClient.getTopic(WEB_SOCKET_TOPIC_TEST);
        topic.addListener(SimpleTextMessage.class, (channel, message) -> webSocketService.sendSimpleMessage(message));
    }

    @Override
    public int getOrder() {
        return 1;
    }

}
