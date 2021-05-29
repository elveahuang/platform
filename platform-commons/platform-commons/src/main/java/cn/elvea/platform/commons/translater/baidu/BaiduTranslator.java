package cn.elvea.platform.commons.translater.baidu;

import cn.elvea.platform.commons.translater.LanguageConverter;
import cn.elvea.platform.commons.translater.Translator;
import cn.elvea.platform.commons.utils.CollectionUtils;
import cn.elvea.platform.commons.utils.HttpComponentsUtils;
import cn.elvea.platform.commons.utils.JsonUtils;
import cn.elvea.platform.commons.utils.StringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 百度翻译器
 * https://api.fanyi.baidu.com/
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class BaiduTranslator implements Translator {

    private final Config config;

    private final LanguageConverter converter = new BaiduTranslator.BaiduLanguageConverter();

    public BaiduTranslator(Config config) {
        this.config = config;
    }

    /**
     * @see Translator#translate(String, String, String)
     */
    @Override
    public String translate(String sourceLang, String targetLang, String text) {
        String salt = this.generateSalt();
        String sign = this.generateSign(salt, text);
        Map<String, String> params = new HashMap<>();
        params.put("from", this.converter.convert(sourceLang));
        params.put("to", this.converter.convert(targetLang));
        params.put("q", text);
        params.put("appid", this.config.getAppId());
        params.put("salt", salt);
        params.put("sign", sign);
        try {
            String resp = HttpComponentsUtils.get(this.config.getEndpoint(), params);
            Response result = JsonUtils.toObject(resp, Response.class);
            if (result != null && result.getError_code() != null && result.getError_code() > 0) {
                log.error("BaiduTranslator translate failed. code - [{}]. resp - [{}]", result.getError_code(), resp);
            } else {
                if (result != null && CollectionUtils.isNotEmpty(result.getTrans_result())) {
                    return result.getTrans_result().get(0).getDst();
                }
            }
        } catch (Exception e) {
            log.error("BaiduTranslator translate failed.", e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取十位随机数
     */
    public String generateSalt() {
        return RandomStringUtils.randomNumeric(10);
    }

    /**
     * 获取十位随机数
     */
    public String generateSign(String salt, String text) {
        return MD5Utils.md5(this.config.getAppId() + text + salt + this.config.getSecretKey());
    }

    /**
     * 百度翻译器配置
     */
    @Data
    @NoArgsConstructor
    public static class Config {
        private String endpoint = "https://fanyi-api.baidu.com/api/trans/vip/translate";
        private String appId = "";
        private String secretKey = "";
    }

    /**
     * 响应结果
     */
    @Data
    @NoArgsConstructor
    public static class Response {
        /**
         * 源语言
         */
        private String from;
        /**
         * 目标语言
         */
        private String to;
        /**
         * 翻译结果
         */
        private List<TransResult> trans_result;
        /**
         * 错误码
         */
        private Integer error_code;
    }

    /**
     * 响应结果
     */
    @Data
    @NoArgsConstructor
    public static class TransResult {
        /**
         * 原文
         */
        private String src;
        /**
         * 译文
         */
        private String dst;
    }

    /**
     * 语言转换
     * https://fanyi-api.baidu.com/doc/21
     */
    public static class BaiduLanguageConverter implements LanguageConverter {

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
                    return "cht";
                case "en_us":
                case "en_uk":
                case "en":
                    return "en";
                default:
                    return language;
            }
        }

    }

    /**
     * MD5
     */
    public static class MD5Utils {

        private static final char[] hexDigits = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'
        };

        /**
         * 获得一个字符串的MD5值
         *
         * @param input 输入的字符串
         * @return 输入字符串的MD5值
         */
        public static String md5(String input) {
            if (input == null) {
                return null;
            }

            try {
                // 拿到一个MD5转换器（如果想要SHA1参数换成”SHA1”）
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                // 输入的字符串转换成字节数组
                byte[] inputByteArray = input.getBytes(StandardCharsets.UTF_8);
                // inputByteArray是输入字符串转换得到的字节数组
                messageDigest.update(inputByteArray);
                // 转换并返回结果，也是字节数组，包含16个元素
                byte[] resultByteArray = messageDigest.digest();
                // 字符数组转换成字符串返回
                return byteArrayToHex(resultByteArray);
            } catch (Exception e) {
                return null;
            }
        }

        private static String byteArrayToHex(byte[] byteArray) {
            // new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））
            char[] resultCharArray = new char[byteArray.length * 2];
            // 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去
            int index = 0;
            for (byte b : byteArray) {
                resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
                resultCharArray[index++] = hexDigits[b & 0xf];
            }
            // 字符数组组合成字符串返回
            return new String(resultCharArray);
        }

    }

}
