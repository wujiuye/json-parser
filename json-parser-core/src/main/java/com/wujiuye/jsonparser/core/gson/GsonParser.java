package com.wujiuye.jsonparser.core.gson;

import com.google.gson.Gson;
import com.wujiuye.jsonparser.core.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

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

}
