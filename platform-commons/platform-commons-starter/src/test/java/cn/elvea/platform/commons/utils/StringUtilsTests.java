package cn.elvea.platform.commons.utils;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * StringUtilsTests
 *
 * @author elvea
 * @since 0.0.1
 */
public class StringUtilsTests {

    @Test
    public void test() {
        String text = "he's name";
        System.out.println(text.replaceAll("'", "\\\\'"));
        System.out.println(text.replaceAll("'", "\\\\'"));
        Properties properties = new Properties();
        properties.put("a", text.replaceAll("'", "\\'"));
        properties.put("b", text.replaceAll("'", "\\\\'"));
        properties.put("c", text.replaceAll("'", "\\\\\\\\'"));
        properties.put("d", "\\");
        properties.put("e", "\\\\");
        File propertiesFile = new File("D://messages.properties");
        try (OutputStream output = new FileOutputStream(propertiesFile)) {
            properties.store(output, "2021");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
