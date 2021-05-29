package cn.elvea.platform.commons.config;

import cn.elvea.platform.base.constants.DateTimeConstants;
import cn.elvea.platform.base.constants.LocaleConstants;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.ZoneId;

/**
 * Config
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
public class Config implements Serializable {

    /**
     * 调试模式
     */
    private boolean debug = false;

    /**
     * 指定用户时区
     */
    private ZoneId userZoneId = DateTimeConstants.ZONE_ID_DEFAULT;

    /**
     * 指定系统时区
     */
    private ZoneId systemZoneId = DateTimeConstants.ZONE_ID_DEFAULT;

    /**
     * 指定系统语言
     */
    private String language = LocaleConstants.DEFAULT_LANGUAGE;

}
