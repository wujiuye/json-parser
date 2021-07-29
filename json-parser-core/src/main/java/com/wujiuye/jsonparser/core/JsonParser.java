package com.wujiuye.jsonparser.core;

import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * @author wujiuye 2020/04/26
 */
public interface JsonParser {

    <T> String toJsonString(T obj);

    <T> T fromJson(String jsonStr, Class<T> tClass);

    <T> T fromJson(InputStream jsonIn, Class<T> tClass);

    <T> T fromJson(String jsonStr, Type type);

    <T> T fromJson(InputStream jsonIn, Type type);

}
