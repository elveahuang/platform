package cn.elvea.platform.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 加密编码工具类单元测试
 *
 * @author elvea
 * @since 0.0.1
 */
@Slf4j
public class EncryptUtilsTests {

    @Test
    public void baseTest() {
        String key = EncryptUtils.generateKey(16);
        System.out.println(key);
        Assertions.assertNotNull(key);
        Assertions.assertEquals(EncryptUtils.decrypt(EncryptUtils.encrypt(key)), key);
    }

    @Test
    public void base64Test() {
        String text = "ASDqwe~!@#$%^&*()";
        Assertions.assertEquals(EncryptUtils.decodeBase64(EncryptUtils.encodeBase64(text)), text);
    }

    @Test
    public void hexTest() {
        String text = "ASDqwe~!@#$%^&*()";
        System.out.println(EncryptUtils.encodeHex(text));
        Assertions.assertEquals(EncryptUtils.decodeHexAsString(EncryptUtils.encodeHex(text)), text);
    }

    @Test
    public void aesTest() {
        String text = "ASDqwe~!@#$%^&*()";
        Assertions.assertEquals(EncryptUtils.aesDecrypt(EncryptUtils.aesEncrypt(text)), text);
    }

    @Test
    public void desTest() {
        String iv = EncryptUtils.generateKey(8);
        String key = EncryptUtils.generateKey(16);
        Assertions.assertNotNull(key);
        Assertions.assertNotNull(iv);
        System.out.println("generate key & iv");
        System.out.printf("iv [%s] - key - [%s]", iv, key);

        String text = "aSd~!@#$%^&*()";
        String encrypted = EncryptUtils.desEncrypt(text);
        String decrypted = EncryptUtils.desDecrypt(encrypted);
        Assertions.assertEquals(decrypted, text);
        System.out.println("encrypt and decrypt test");
        System.out.printf("text [%s] - encrypted - [%s] - decrypted - [%s]", text, encrypted, decrypted);
    }

    @Test
    public void md5Test() {
        String text = "ASDqwe~!@#$%^&*()";
        Assertions.assertNotNull(EncryptUtils.md5(text));
    }

    @Test
    public void shaTest() {
        String text = "ASDqwe~!@#$%^&*()";
        Assertions.assertNotNull(EncryptUtils.sha(text));
    }

    @Test
    public void urlTest() {
        String text = "http://127.0.0.1";
        String encrypted = EncryptUtils.urlEncode(text);
        String decrypted = EncryptUtils.urlDecode(encrypted);
        Assertions.assertEquals(decrypted, text);
    }

    @Test
    public void xmlTest() {
        String text = "<a>A</a>";
        String encrypted = EncryptUtils.escapeXml(text);
        String decrypted = EncryptUtils.unescapeXml(encrypted);
        Assertions.assertEquals(decrypted, text);
    }

    @Test
    public void htmlTest() {
        String text = "<a>A</a>";
        String encrypted = EncryptUtils.escapeHtml(text);
        String decrypted = EncryptUtils.unescapeHtml(encrypted);
        Assertions.assertEquals(decrypted, text);
    }

}
