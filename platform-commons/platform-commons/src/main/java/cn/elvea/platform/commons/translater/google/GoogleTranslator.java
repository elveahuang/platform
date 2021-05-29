package cn.elvea.platform.commons.translater.google;

import cn.elvea.platform.commons.translater.LanguageConverter;
import cn.elvea.platform.commons.translater.Translator;
import cn.elvea.platform.commons.translater.aliyun.AliyunTranslator;
import cn.elvea.platform.commons.utils.HttpComponentsUtils;
import cn.elvea.platform.commons.utils.JsonUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 谷歌翻译器
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class GoogleTranslator implements Translator {

    private final GoogleTranslator.Config config;

    private final LanguageConverter converter = new AliyunTranslator.AliyunLanguageConverter();

    public GoogleTranslator(GoogleTranslator.Config config) {
        this.config = config;
    }

    /**
     * @see Translator#translate(String, String, String)
     */
    @Override
    public String translate(String sourceLang, String targetLang, String text) {
        Map<String, String> params = new HashMap<>();
        params.put("client", "gtx");
        params.put("dt", "t");
        params.put("sl", this.converter.convert(sourceLang));
        params.put("tl", this.converter.convert(targetLang));
        params.put("q", text);
        try {
            String resp = HttpComponentsUtils.get(this.config.getEndpoint(), params);
            return JsonUtils.getObjectMapper().readTree(resp).get(0).get(0).get(0).asText();
        } catch (Exception e) {
            log.error("GoogleTranslator translate failed.", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 谷歌翻译器配置
     *
     * @author elvea
     * @since 0.0.1
     */
    @Data
    @NoArgsConstructor
    public static class Config {
        private String endpoint = "https://translate.googleapis.com/translate_a/single";
    }

    /**
     * 语言转换
     *
     * @author elvea
     * @since 0.0.1
     */
    public static class GoogleLanguageConverter implements LanguageConverter {
    }

}
