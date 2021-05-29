package cn.elvea.platform.commons.utils;

import java.util.UUID;

/**
 * UUID
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class UuidUtils {

    /**
     * 生成随机UUID
     *
     * @return UUID
     */
    public static String uuid() {
        return UUID.randomUUID().toString();
    }

}
