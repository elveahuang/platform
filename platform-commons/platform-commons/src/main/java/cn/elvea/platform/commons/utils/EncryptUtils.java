package cn.elvea.platform.commons.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.text.StringEscapeUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * 加密工具类
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class EncryptUtils {
    private static final String ENCODING = "UTF-8";
    private static final Charset UTF8 = StandardCharsets.UTF_8;

    private static final String MD5 = "MD5";

    private static final String SHA = "SHA-256";
    private static final String SHA_SALT = "123456781234567812345678";
    private static final int SHA_ITERATIONS = 1024;

    private static final String DES = "DESede";
    private static final String DES_CBC = "DESede/CBC/PKCS5Padding";
    private static final String DEFAULT_DES_KEY = "123456781234567812345678";
    private static final String DEFAULT_DES_IV = "12345678";

    private static final String AES = "AES";
    private static final String AES_CBC = "AES/CBC/PKCS5Padding";
    private static final String DEFAULT_AES_KEY = "1234567812345678";
    private static final String DEFAULT_AES_IV = "1234567812345678";

    private final static SecureRandom random = new SecureRandom();

    // =================================================================================================================
    // Base64
    // =================================================================================================================

    public static String encodeBase64(String input) {
        return encodeBase64(input.getBytes());
    }

    public static String encodeBase64(byte[] input) {
        return Base64.encodeBase64String(input);
    }

    public static String decodeBase64(String input) {
        return decodeBase64(input.getBytes());
    }

    public static String decodeBase64(byte[] input) {
        return new String(Base64.decodeBase64(input));
    }

    // =================================================================================================================
    // AES
    // =================================================================================================================

    public static String aesEncrypt(String input) {
        return aesEncrypt(input, DEFAULT_AES_KEY);
    }

    public static String aesEncrypt(String input, String key) {
        return aesEncrypt(input, key, DEFAULT_AES_IV);
    }

    public static String aesEncrypt(String input, String key, String iv) {
        byte[] result = aes(input.getBytes(), key.getBytes(), iv.getBytes(), Cipher.ENCRYPT_MODE);
        return encryptBytes(result);
    }

    public static String aesDecrypt(String input) {
        return aesDecrypt(input, DEFAULT_AES_KEY);
    }

    public static String aesDecrypt(String input, String key) {
        return aesDecrypt(input, key, DEFAULT_AES_IV);
    }

    public static String aesDecrypt(String input, String key, String iv) {
        byte[] bytes = decryptBytes(input);
        return new String(aes(bytes, key.getBytes(), iv.getBytes(), Cipher.DECRYPT_MODE));
    }

    private static byte[] aes(byte[] input, byte[] key, byte[] iv, int mode) {
        try {
            SecretKey secretKey = new SecretKeySpec(key, AES);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance(AES_CBC);
            cipher.init(mode, secretKey, ivSpec);
            return cipher.doFinal(input);
        } catch (GeneralSecurityException e) {
            throw ExceptionUtils.unchecked(e);
        }
    }

    // =================================================================================================================
    // DES
    // =================================================================================================================

    public static String desEncrypt(String input) {
        return desEncrypt(input, DEFAULT_DES_KEY);
    }

    public static String desEncrypt(String input, String key) {
        return desEncrypt(input, key, DEFAULT_DES_IV);
    }

    public static String desEncrypt(String input, String key, String iv) {
        byte[] result = des(input.getBytes(), key.getBytes(), iv.getBytes(), Cipher.ENCRYPT_MODE);
        return encryptBytes(result);
    }

    public static String desDecrypt(String input) {
        return desDecrypt(input, DEFAULT_DES_KEY);
    }

    public static String desDecrypt(String input, String key) {
        return desDecrypt(input, key, DEFAULT_DES_IV);
    }

    public static String desDecrypt(String input, String key, String iv) {
        byte[] bytes = decryptBytes(input);
        return new String(des(bytes, key.getBytes(), iv.getBytes(), Cipher.DECRYPT_MODE));
    }

    private static byte[] des(byte[] input, byte[] key, byte[] iv, int mode) {
        try {
            final SecretKeySpec secretKeySpec = new SecretKeySpec(key, DES);
            final Cipher cipher = Cipher.getInstance(DES_CBC);
            cipher.init(mode, secretKeySpec, new IvParameterSpec(iv));
            return cipher.doFinal(input);
        } catch (GeneralSecurityException e) {
            throw ExceptionUtils.unchecked(e);
        }
    }

    // =================================================================================================================
    // MD5
    // =================================================================================================================

    public static String md5(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(EncryptUtils.MD5);
            byte[] bytes = messageDigest.digest(input.getBytes());
            return encryptBytes(bytes);
        } catch (GeneralSecurityException e) {
            throw ExceptionUtils.unchecked(e);
        }
    }

    // =================================================================================================================
    // SHA-256
    // =================================================================================================================
    public static byte[] sha(String input) {
        return sha(input, UTF8);
    }

    public static byte[] sha(String input, Charset charset) {
        return sha(input.getBytes(charset));
    }

    public static byte[] sha(byte[] input) {
        return sha(input, SHA_SALT.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] sha(byte[] input, byte[] salt) {
        return sha(input, salt, SHA_ITERATIONS);
    }

    public static byte[] sha(byte[] input, byte[] salt, int iterations) {
        return digest(input, salt, iterations);
    }

    private static byte[] digest(byte[] input, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(SHA);
            if (salt != null) {
                digest.update(salt);
            }
            byte[] result = digest.digest(input);
            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            throw ExceptionUtils.unchecked(e);
        }
    }

    // =================================================================================================================
    // Hex
    // =================================================================================================================

    public static String encodeHex(String input) {
        return encodeHex(input.getBytes());
    }

    public static String encodeHex(byte[] input) {
        return Hex.encodeHexString(input);
    }

    public static byte[] decodeHex(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw ExceptionUtils.unchecked(e);
        }
    }

    public static String decodeHexAsString(String input) {
        return new String(decodeHex(input));
    }

    // =================================================================================================================
    // URL
    // =================================================================================================================

    public static String urlEncode(String url) {
        try {
            return URLEncoder.encode(url, ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw ExceptionUtils.unchecked(e);
        }
    }

    public static String urlDecode(String url) {
        try {
            return URLDecoder.decode(url, ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw ExceptionUtils.unchecked(e);
        }
    }

    // =================================================================================================================
    // HTML
    // =================================================================================================================

    public static String escapeHtml(String html) {
        return StringEscapeUtils.escapeHtml4(html);
    }

    public static String unescapeHtml(String htmlEscaped) {
        return StringEscapeUtils.unescapeHtml4(htmlEscaped);
    }

    // =================================================================================================================
    // XML
    // =================================================================================================================

    public static String escapeXml(String xml) {
        return StringEscapeUtils.escapeXml11(xml);
    }

    public static String unescapeXml(String xmlEscaped) {
        return StringEscapeUtils.unescapeXml(xmlEscaped);
    }

    // =================================================================================================================
    // Base
    // =================================================================================================================

    public static String encrypt(String input) {
        return encryptBytes(input.getBytes());
    }

    public static String decrypt(String input) {
        return new String(decryptBytes(input));
    }

    public static String encryptBytes(byte[] input) {
        return encodeHex(Base64.encodeBase64(input));
    }

    public static byte[] decryptBytes(String input) {
        return Base64.decodeBase64(decodeHex(input));
    }

    public static String generateKey(int numBytes) {
        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return encodeHex(bytes);
    }

}
