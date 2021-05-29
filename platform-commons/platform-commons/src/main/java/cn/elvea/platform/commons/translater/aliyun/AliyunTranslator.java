package cn.elvea.platform.commons.translater.aliyun;

import cn.elvea.platform.commons.translater.LanguageConverter;
import cn.elvea.platform.commons.translater.Translator;
import cn.elvea.platform.commons.utils.StringUtils;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alimt.model.v20181012.TranslateGeneralRequest;
import com.aliyuncs.alimt.model.v20181012.TranslateGeneralResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 阿里云机器翻译
 * https://www.aliyun.com/product/ai/alimt
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class AliyunTranslator implements Translator {

    private final Config config;

    private final LanguageConverter converter = new AliyunLanguageConverter();

    public AliyunTranslator(Config config) {
        this.config = config;
    }

    /**
     * @see Translator#translate(String, String, String)
     */
    @Override
    public String translate(String sourceLang, String targetLang, String text) {
        DefaultProfile profile = DefaultProfile.getProfile(config.getRegionId(), config.getAccessKeyId(), config.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);
        TranslateGeneralRequest request = new TranslateGeneralRequest();
        request.setSysRegionId(config.getRegionId());
        request.setFormatType("text");
        request.setSourceLanguage(this.converter.convert(sourceLang));
        request.setTargetLanguage(this.converter.convert(targetLang));
        request.setSourceText(text);
        try {
            TranslateGeneralResponse response = client.getAcsResponse(request);
            return response.getData().getTranslated();
        } catch (ClientException e) {
            log.error("AliyunTranslator translate failed. code - [{}]. msg - [{}]. id - [{}]", e.getErrCode(), e.getErrMsg(), e.getRequestId());
            log.error("AliyunTranslator translate failed.", e);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 阿里云翻译器配置
     */
    @Data
    @NoArgsConstructor
    public static class Config {
        /**
         * Endpoint
         */
        private String regionId = "cn-hangzhou";
        /**
         * Access Key Id
         */
        private String accessKeyId = "";
        /**
         * Access Key Secret
         */
        private String accessKeySecret = "";
    }

    /**
     * 阿里云翻译器语言转换器
     * https://help.aliyun.com/document_detail/158269.html
     */
    public static class AliyunLanguageConverter implements LanguageConverter {

        /**
         * @see LanguageConverter#convert(String)
         */
        @Override
        public String convert(String language) {
            language = StringUtils.hasText(language) ? language.trim().toLowerCase() : "";
            switch (language) {
                case "zh_cn":
                case "zh":
                    return "zh";
                case "zh_tw":
                    return "zh-tw";
                case "en_us":
                case "en_uk":
                case "en":
                    return "en";
                default:
                    return language;
            }
        }

    }

}
