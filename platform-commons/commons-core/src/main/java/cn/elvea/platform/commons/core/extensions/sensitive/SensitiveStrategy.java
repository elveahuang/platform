package cn.elvea.platform.commons.core.extensions.sensitive;

import cn.elvea.platform.commons.core.utils.SensitiveUtils;
import lombok.AllArgsConstructor;

import java.util.function.Function;

/**
 * @author elvea
 * @since 0.0.1
 */
@AllArgsConstructor
public enum SensitiveStrategy {
    /**
     * 身份证
     */
    ID_CARD(SensitiveUtils::idCard),
    /**
     * 手机号码
     */
    MOBILE(SensitiveUtils::mobileNumber);

    private final Function<String, String> handler;

    public Function<String, String> handler() {
        return handler;
    }

}
