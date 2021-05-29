package cn.elvea.platform.commons.id;

import cn.elvea.platform.commons.exception.SystemException;
import cn.elvea.platform.commons.id.impl.IdWork;
import cn.elvea.platform.commons.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.util.Assert;

import javax.annotation.PreDestroy;
import java.util.concurrent.TimeUnit;

import static cn.elvea.platform.commons.id.IdConfig.CACHE_KEY_ID_WORKER;
import static cn.elvea.platform.commons.id.IdConfig.ID_WORKER_TYPE_AUTO;

/**
 * IdWorkerManager
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class IdWorkerManager {

    private final IdConfig config;
    private final RedissonClient client;

    private String currentApplicationPid = null;
    private String currentApplicationName = null;
    private String currentApplicationPort = null;
    private String currentLocalAddress = null;
    private long currentDatacenterId = 0;
    private long currentWorkerId = 0;

    public IdWorkerManager(IdConfig config, RedissonClient client) {
        Assert.notNull(config, "IdWorkerConfig.config must not be null!");
        Assert.notNull(client, "IdWorkerConfig.client must not be null!");
        this.config = config;
        this.client = client;
    }

    public IdWorker createIdWorker() throws Exception {
        if (ID_WORKER_TYPE_AUTO.equalsIgnoreCase(this.config.getType())) {
            // 获取机器IP后，模32，获得一个0-31的整数，作为初始的工作机器ID
            String currentLocalAddress = IpUtils.getLocalIpAddress();
            long ip = Long.parseLong(currentLocalAddress.replaceAll("\\.", ""));

            this.currentApplicationPid = this.config.getApplicationPid();
            this.currentApplicationName = this.config.getApplicationName();
            this.currentApplicationPort = this.config.getApplicationPort();
            this.currentLocalAddress = currentLocalAddress;
            this.currentDatacenterId = this.config.getDatacenterId();
            this.currentWorkerId = Long.hashCode(ip) & 32;

            this.currentWorkerId = initWorkerId();
        } else {
            this.currentDatacenterId = this.config.getDatacenterId();
            this.currentWorkerId = this.config.getWorkerId();
        }
        log.debug("Create id worker instance [{}, {}]", this.currentDatacenterId, this.currentWorkerId);
        return new IdWork(this.currentDatacenterId, this.currentWorkerId);
    }

    private long initWorkerId() {
        if (this.currentWorkerId >= 0 && this.currentWorkerId < IdWork.MAX_WORKER_ID && this.registerWorkerNode()) {
            return this.currentWorkerId;
        } else {
            if (checkAvailableWorkerId() && this.registerWorkerNode()) {
                return this.currentWorkerId;
            } else {
                throw new SystemException("No available worker id.");
            }
        }
    }

    private boolean checkAvailableWorkerId() {
        boolean result = false;
        RMapCache<String, IdWorkerNode> idWorkerMap = this.client.getMapCache(CACHE_KEY_ID_WORKER);
        for (long i = 0; i < 32; i++) {
            if (!idWorkerMap.containsKey(getWorkerNodeKey(this.currentApplicationName, this.currentDatacenterId, i))) {
                result = true;
                this.currentWorkerId = i;
                break;
            }
        }
        return result;
    }

    private String getWorkerNodeKey(String applicationName, long datacenterId, long workerId) {
        return String.format("%s-%s-%s", applicationName, datacenterId, workerId);
    }

    private boolean registerWorkerNode() {
        log.debug("registerIdWorkerNode...");

        String key = getWorkerNodeKey(this.currentApplicationName, this.currentDatacenterId, this.currentWorkerId);

        RMapCache<String, IdWorkerNode> workerMap = this.client.getMapCache(CACHE_KEY_ID_WORKER);
        if (workerMap.containsKey(key)) {
            IdWorkerNode wn = workerMap.get(key);
            return wn.getApplicationPid().equalsIgnoreCase(this.currentApplicationPid) &&
                    wn.getIpAddress().equalsIgnoreCase(this.currentLocalAddress);
        } else {
            IdWorkerNode node = IdWorkerNode.builder()
                    .applicationPid(this.currentApplicationPid)
                    .applicationName(this.currentApplicationName)
                    .applicationPort(this.currentApplicationPort)
                    .datacenterId(this.currentDatacenterId)
                    .workerId(this.currentWorkerId)
                    .ipAddress(this.currentLocalAddress)
                    .build();
            workerMap.putIfAbsent(key, node, 1, TimeUnit.DAYS);
            return true;
        }
    }

    /**
     * 更新工作机器
     */
    public void updateWorkerNode() {
        if (ID_WORKER_TYPE_AUTO.equalsIgnoreCase(this.config.getType())) {
            log.debug("updateIdWorkerNode...");
            String key = getWorkerNodeKey(this.currentApplicationName, this.currentDatacenterId, this.currentWorkerId);
            log.debug("updateIdWorkerNode key - {}.", key);
            RMapCache<String, IdWorkerNode> workerMap = this.client.getMapCache(CACHE_KEY_ID_WORKER);
            if (workerMap.containsKey(key)) {
                IdWorkerNode node = workerMap.get(key);
                workerMap.put(key, node, 1, TimeUnit.DAYS);
                log.debug("updateIdWorkerNode key - {} success.", key);
            } else {
                log.debug("updateIdWorkerNode key - {} fail.", key);
            }
        }
    }

    @PreDestroy
    public void removeWorkerNode() {
        log.debug("removeIdWorkerNode...");
        String key = getWorkerNodeKey(this.currentApplicationName, this.currentDatacenterId, this.currentWorkerId);
        RMapCache<String, IdWorkerNode> workerMap = this.client.getMapCache(CACHE_KEY_ID_WORKER);
        workerMap.remove(key);
        log.debug("removeIdWorkerNode key - {} success.", key);
    }

}
