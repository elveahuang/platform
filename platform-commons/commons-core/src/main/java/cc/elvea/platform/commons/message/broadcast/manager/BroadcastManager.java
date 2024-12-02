package cc.elvea.platform.commons.message.broadcast.manager;

import cc.elvea.platform.commons.message.broadcast.BroadcastSender;
import cc.elvea.platform.commons.message.broadcast.rabbit.RabbitBroadcastSender;

/**
 * @author elvea
 * @since 24.1.0
 */
public interface BroadcastManager {

    BroadcastSender getSender();

    RabbitBroadcastSender getRabbitSender();

}
