package cn.elvea.platform.commons.core.extensions.translator;

import cn.elvea.platform.commons.core.extensions.translator.aliyun.AliyunTranslator;
import cn.elvea.platform.commons.core.extensions.translator.baidu.BaiduTranslator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author elvea
 * @since 0.0.1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslatorConfig {
    @Builder.Default
    private Boolean enabled = Boolean.FALSE;
    @Builder.Default
    private TranslatorType type = TranslatorType.Aliyun;
    @Builder.Default
    private BaiduTranslator.Config baidu = new BaiduTranslator.Config();
    @Builder.Default
    private AliyunTranslator.Config aliyun = new AliyunTranslator.Config();
}
