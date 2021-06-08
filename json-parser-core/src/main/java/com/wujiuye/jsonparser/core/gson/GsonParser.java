package com.wujiuye.jsonparser.core.gson;

import com.google.gson.Gson;
import com.wujiuye.jsonparser.core.JsonParser;
import com.wujiuye.jsonparser.core.TypeReference;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * gson
 *
 * @author wujiuye 2020/04/26
 */
public class GsonParser implements JsonParser {

    private Gson gson;

    public GsonParser(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> String toJsonString(T obj) {
        return gson.toJson(obj);
    }

    @Override
    public <T> T fromJson(String jsonStr, Class<T> tClass) {
        return this.gson.fromJson(jsonStr, tClass);
    }

    @Override
    public <T> T fromJson(InputStream jsonIn, Class<T> tClass) {
        return this.gson.fromJson(new InputStreamReader(jsonIn), tClass);
    }

    @Override
    public <T> T fromJson(String jsonStr, Type type) {
        return this.gson.fromJson(jsonStr, type);
    }

    @Override
    public <T> T fromJson(InputStream jsonIn, Type type) {
        return this.gson.fromJson(new InputStreamReader(jsonIn), type);
    }

    @Override
    public <T> List<T> fromJsonArray(String jsonStr, TypeReference<List<T>> typeReference) {
        return this.gson.fromJson(jsonStr, typeReference.getType());
    }

    @Override
    public <T> List<T> fromJsonArray(InputStream jsonIn, TypeReference<List<T>> typeReference) {
        return this.gson.fromJson(new InputStreamReader(jsonIn), typeReference.getType());
    }

    @Override
    public <K, V> Map<K, V> fromJsonMap(String jsonStr, TypeReference<Map<K, V>> typeReference) {
        return this.gson.fromJson(jsonStr, typeReference.getType());
    }

    @Override
    public <K, V> Map<K, V> fromJsonMap(InputStream jsonIn, TypeReference<Map<K, V>> typeReference) {
        return this.gson.fromJson(new InputStreamReader(jsonIn), typeReference.getType());
    }

}
