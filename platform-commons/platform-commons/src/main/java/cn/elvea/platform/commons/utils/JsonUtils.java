package cn.elvea.platform.commons.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

/**
 * 基于Jackson封装的工具类
 *
 * @author elvea
 * @since 0.0.1
 */
public abstract class JsonUtils {

    private final static JsonMapper objectMapper = JsonMapper.builder()
            .enable(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES)
            .enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS)
            .enable(JsonReadFeature.ALLOW_MISSING_VALUES)
            .enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .visibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
            .addModule(new JavaTimeModule())
            .addModule(new Jdk8Module())
            .build();

    private final static JsonMapper cacheObjectMapper = JsonMapper.builder()
            .enable(JsonReadFeature.ALLOW_UNQUOTED_FIELD_NAMES)
            .enable(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS)
            .enable(JsonReadFeature.ALLOW_MISSING_VALUES)
            .enable(JsonReadFeature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .visibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
            .activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY)
            .addModule(new JavaTimeModule())
            .addModule(new Jdk8Module())
            .build();

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static ObjectMapper getCacheObjectMapper() {
        return cacheObjectMapper;
    }

    /**
     * 把对象转换成JSON文本
     */
    public static String toJson(Object obj) throws Exception {
        return getObjectMapper().writeValueAsString(obj);
    }

    /**
     * 把JSON文本转成对象
     */
    public static <T> T toObject(String json, Class<T> clazz) throws Exception {
        return getObjectMapper().readValue(json, clazz);
    }

    /**
     * 把JSON文本转成对象
     */
    public static <T> T toObject(String json, TypeReference<T> typeReference) throws Exception {
        return getObjectMapper().readValue(json, typeReference);
    }

}
