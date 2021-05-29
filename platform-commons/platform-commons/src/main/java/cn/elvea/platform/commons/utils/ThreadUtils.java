package cn.elvea.platform.commons.utils;

import java.util.concurrent.TimeUnit;

/**
 * ThreadUtils
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class ThreadUtils {

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void sleep(TimeUnit timeUnit, long timeout) {
        try {
            timeUnit.sleep(timeout);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
