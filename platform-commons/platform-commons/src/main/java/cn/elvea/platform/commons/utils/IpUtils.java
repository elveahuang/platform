package cn.elvea.platform.commons.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * IpUtils
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class IpUtils {

    public static String getLocalIpAddress() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

}
