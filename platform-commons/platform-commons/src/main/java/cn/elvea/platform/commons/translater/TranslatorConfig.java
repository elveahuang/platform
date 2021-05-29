package cn.elvea.platform.commons.translater;

import cn.elvea.platform.commons.translater.aliyun.AliyunTranslator;
import cn.elvea.platform.commons.translater.baidu.BaiduTranslator;
import cn.elvea.platform.commons.translater.google.GoogleTranslator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TranslatorConfig
 *
 * @author elvea
 * @since 0.0.1
 */
@Data
@NoArgsConstructor
public class TranslatorConfig implements Serializable {
    private TranslatorType type = TranslatorType.GOOGLE;
    private BaiduTranslator.Config baidu = new BaiduTranslator.Config();
    private GoogleTranslator.Config google = new GoogleTranslator.Config();
    private AliyunTranslator.Config aliyun = new AliyunTranslator.Config();
}
