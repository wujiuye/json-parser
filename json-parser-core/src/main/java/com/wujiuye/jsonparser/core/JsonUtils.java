package com.wujiuye.jsonparser.core;

import com.wujiuye.jsonparser.core.gson.GsonParserFactory;
import com.wujiuye.jsonparser.core.jackson.JacksonParserFactory;
import com.wujiuye.jsonparser.core.util.StringUtils;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * json序列化适配器
 *
 * @author wujiuye 2020/04/26
 */
public class JsonUtils {

    private static AtomicReference<AbstractJsonParserFactory<? extends JsonParser>> chooseJsonParserFactory
            = new AtomicReference<>(newJsonParserFactory(new SerializeConfig()));

    private static AbstractJsonParserFactory<? extends JsonParser> newJsonParserFactory(SerializeConfig config) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            classLoader.loadClass("com.google.gson.Gson");
            return new GsonParserFactory(config);
        } catch (ClassNotFoundException e) {
            try {
                classLoader.loadClass("com.fasterxml.jackson.databind.ObjectMapper");
                return new JacksonParserFactory(config);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException("未找到任何json包，请先在当前项目的依赖配置文件中加入 gson或fackson");
            }
        }
    }

    public static synchronized void resetSerializeConfig(SerializeConfig config) {
        chooseJsonParserFactory.set(newJsonParserFactory(config));
    }

    private static JsonParser getJsonParser() {
        return chooseJsonParserFactory.get().getJsonParser();
    }

    public static <T> String toJsonString(T obj) {
        return chooseJsonParserFactory.get().getJsonParser(obj.getClass()).toJsonString(obj);
    }

    public static <T> T fromJson(String jsonStr, Class<T> tClass) {
        if (StringUtils.isNullOrEmpty(jsonStr)) {
            return null;
        }
        return getJsonParser().fromJson(jsonStr, tClass);
    }

    public static <T> T fromJson(InputStream jsonIn, Class<T> tClass) {
        return getJsonParser().fromJson(jsonIn, tClass);
    }

    public static <T> T fromJson(String jsonStr, Type type) {
        return getJsonParser().fromJson(jsonStr, type);
    }

    public static <T> T fromJson(InputStream jsonIn, Type type) {
        return getJsonParser().fromJson(jsonIn, type);
    }

    public static <T> List<T> fromJsonArray(String jsonStr, TypeReference<List<T>> typeReference) {
        if (StringUtils.isNullOrEmpty(jsonStr)) {
            return null;
        }
        return getJsonParser().fromJson(jsonStr, typeReference.getType());
    }

    public static <T> List<T> fromJsonArray(InputStream jsonIn, TypeReference<List<T>> typeReference) {
        return getJsonParser().fromJson(jsonIn, typeReference.getType());
    }

    public static <K, V> Map<K, V> fromJsonMap(String jsonStr, TypeReference<Map<K, V>> typeReference) {
        if (StringUtils.isNullOrEmpty(jsonStr)) {
            return null;
        }
        return getJsonParser().fromJson(jsonStr, typeReference.getType());
    }

    public static <K, V> Map<K, V> fromJsonMap(InputStream jsonIn, TypeReference<Map<K, V>> typeReference) {
        return getJsonParser().fromJson(jsonIn, typeReference.getType());
    }

}
