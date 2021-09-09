package com.wujiuye.jsonparser.core.gson1x;

import com.google.gson.Gson;
import com.wujiuye.jsonparser.core.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * gson 1.x版本适配
 *
 * @author wujiuye 2020/04/26
 */
public class Gson1xParser implements JsonParser {

    private final Gson gson;

    public Gson1xParser(Gson gson) {
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

}
