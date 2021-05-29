package cn.elvea.platform.commons.translater;

import cn.elvea.platform.commons.CommonsTestApplicationTests;
import cn.elvea.platform.commons.translater.google.GoogleTranslator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 谷歌翻译单元测试
 *
 * @author elvea
 * @since 0.0.1
 */
public class GoogleTranslatorTests extends CommonsTestApplicationTests {

    @Autowired(required = false)
    GoogleTranslator translator;

    @Test
    public void test() {
        System.out.println(translator.translate("zh_CN", "en_US", "我"));
    }

}
