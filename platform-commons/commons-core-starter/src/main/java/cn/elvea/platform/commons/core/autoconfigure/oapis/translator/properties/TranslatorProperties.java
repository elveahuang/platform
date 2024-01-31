package cn.elvea.platform.commons.core.autoconfigure.oapis.translator.properties;

import cn.elvea.platform.commons.core.oapis.translator.TranslatorType;
import cn.elvea.platform.commons.core.oapis.translator.aliyun.AliyunTranslator;
import cn.elvea.platform.commons.core.oapis.translator.baidu.BaiduTranslator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.Serializable;

/**
 * @author elvea
 * @since 24.1.0
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = TranslatorProperties.PREFIX)
public class TranslatorProperties implements Serializable {

    public static final String PREFIX = "platform.translator";

    private boolean enabled = false;

    private TranslatorType type = TranslatorType.Aliyun;

    @NestedConfigurationProperty
    private BaiduTranslator.Config baidu = new BaiduTranslator.Config();

    @NestedConfigurationProperty
    private AliyunTranslator.Config aliyun = new AliyunTranslator.Config();

}
