package cn.elvea.platform.commons.core.utils;

import cn.hutool.core.util.DesensitizedUtil;

/**
 * @author elvea
 * @since 0.0.1
 */
public abstract class SensitiveUtils {

    public static String idCard(String idCard) {
        if (StringUtils.isEmpty(idCard)) {
            return "";
        }
        return DesensitizedUtil.idCardNum(idCard, 1, 2);
    }

    public static String mobileNumber(String mobileNumber) {
        if (StringUtils.isEmpty(mobileNumber)) {
            return "";
        }
        return DesensitizedUtil.mobilePhone(mobileNumber);
    }

}
