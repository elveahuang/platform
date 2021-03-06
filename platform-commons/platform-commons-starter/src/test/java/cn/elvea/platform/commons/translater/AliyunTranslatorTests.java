package cn.elvea.platform.commons.translater;

import cn.elvea.platform.commons.CommonsTestApplicationTests;
import cn.elvea.platform.commons.translater.aliyun.AliyunTranslator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 谷歌翻译单元测试
 *
 * @author elvea
 * @since 0.0.1
 */
public class AliyunTranslatorTests extends CommonsTestApplicationTests {

    @Autowired
    AliyunTranslator translator;

    @Test
    public void test() {
        System.out.println(translator.translate("zh_CN", "en_Us", "我"));
        System.out.println(translator.translate("zh_CN", "en_UK", "我"));
        System.out.println(translator.translate("zh_CN", "en", "我"));
        System.out.println(translator.translate("zh_cn", "zh_TW", "我"));
        System.out.println(translator.translate("zh", "zh_tw", "我"));
    }

}
