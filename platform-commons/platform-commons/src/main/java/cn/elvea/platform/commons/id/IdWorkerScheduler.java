package cn.elvea.platform.commons.id;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.Assert;

/**
 * IdWorkerScheduler
 *
 * @author elvea
 * @since 0.0.1
 */
public class IdWorkerScheduler {

    private final IdWorkerManager idWorkerManager;

    public IdWorkerScheduler(IdWorkerManager idWorkerManager) {
        Assert.notNull(idWorkerManager, "IdWorkerManager.idWorkerManager must not be null!");
        this.idWorkerManager = idWorkerManager;
    }

    @Scheduled(fixedDelay = 60000)
    private void updateWorkerNodeExpire() {
        idWorkerManager.updateWorkerNode();
    }

}
