package cn.elvea.platform.commons.core.extensions.log.aspect;

import cn.elvea.platform.commons.core.extensions.log.LogManager;

/**
 * @author elvea
 * @since 0.0.1
 */
public abstract class AbstractLogAspect {

    protected final LogManager logManager;

    public AbstractLogAspect(LogManager logManager) {
        this.logManager = logManager;
    }

}
