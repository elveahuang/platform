package cn.elvea.platform.commons.translater;

import cn.elvea.platform.commons.CommonsTestApplicationTests;
import cn.elvea.platform.commons.translater.baidu.BaiduTranslator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 谷歌翻译单元测试
 *
 * @author elvea
 * @since 0.0.1
 */
public class BaiduTranslatorTests extends CommonsTestApplicationTests {

    @Autowired(required = false)
    BaiduTranslator translator;

    @Test
    public void test() throws Exception {
        String text = "Add a survey";
        String zh_text = "添加调查问卷";
        System.out.println(translator.translate("en", "yue", text));
        Thread.sleep(1000);
        System.out.println(translator.translate("en", "cht", text));
        Thread.sleep(1000);
        System.out.println(translator.translate("en", "fra", text));
        Thread.sleep(1000);
        System.out.println(translator.translate("en", "ara", text));
        Thread.sleep(1000);
        System.out.println(translator.translate("en", "th", text));
        Thread.sleep(1000);
        System.out.println(translator.translate("en", "ru", text));
        Thread.sleep(1000);
        System.out.println(translator.translate("en", "vie", text));
        Thread.sleep(1000);
        System.out.println(translator.translate("en", "id", text));
        Thread.sleep(1000);
        System.out.println(translator.translate("zh", "fil", zh_text));
        Thread.sleep(1000);
        System.out.println(translator.translate("zh", "bur", zh_text));
        Thread.sleep(1000);
        System.out.println(translator.translate("zh", "urd", zh_text));
    }

}
