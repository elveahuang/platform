package cn.elvea.platform.commons.translater;

import cn.elvea.platform.commons.CommonsTestApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * BaseTranslatorTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class BaseTranslatorTests extends CommonsTestApplicationTests {

    @Autowired(required = false)
    TranslatorFactory translatorFactory;

    @Test
    public void test() throws Exception {
        String text = "Add a survey";
        String zh_text = "添加调查问卷";
        System.out.println(translatorFactory.getTranslator().translate("en", "yue", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("en", "cht", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("en", "fra", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("en", "ar", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("en", "th", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("en", "ru", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("en", "vi", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("en", "id", text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("zh", "fil", zh_text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("zh", "my", zh_text));
        Thread.sleep(1000);
        System.out.println(translatorFactory.getTranslator().translate("zh", "ur", zh_text));
    }

}
