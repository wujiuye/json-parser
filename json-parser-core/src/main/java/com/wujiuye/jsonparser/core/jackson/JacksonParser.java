package com.wujiuye.jsonparser.core.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.wujiuye.jsonparser.core.JsonParser;
import com.wujiuye.jsonparser.core.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * jackson
 *
 * @author wujiuye 2020/04/26
 */
public class JacksonParser implements JsonParser {

    private ObjectMapper objectMapper;

    ObjectMapper getObjectMapper() {
        return this.objectMapper;
    }

    public JacksonParser(ObjectMapperSub objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> String toJsonString(T obj) {
        try {
            return this.objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T fromJson(String jsonStr, Class<T> tClass) {
        try {
            return this.objectMapper.readValue(jsonStr, tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T fromJson(InputStream jsonIn, Class<T> tClass) {
        try {
            return this.objectMapper.readValue(jsonIn, tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T fromJson(String jsonStr, Type type) {
        try {
            TypeFactory typeFactory = this.objectMapper.getTypeFactory();
            JavaType javaType = typeFactory.constructType(type);
            return this.objectMapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T fromJson(InputStream jsonIn, Type type) {
        try {
            TypeFactory typeFactory = this.objectMapper.getTypeFactory();
            JavaType javaType = typeFactory.constructType(type);
            return this.objectMapper.readValue(jsonIn, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> fromJsonArray(String jsonStr, TypeReference<List<T>> typeReference) {
        try {
            return this.objectMapper.readValue(jsonStr, new com.fasterxml.jackson.core.type.TypeReference<List<T>>() {
                @Override
                public Type getType() {
                    return typeReference.getType();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> fromJsonArray(InputStream jsonIn, TypeReference<List<T>> typeReference) {
        try {
            return this.objectMapper.readValue(jsonIn, new com.fasterxml.jackson.core.type.TypeReference<List<T>>() {
                @Override
                public Type getType() {
                    return typeReference.getType();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <K, V> Map<K, V> fromJsonMap(String jsonStr, TypeReference<Map<K, V>> typeReference) {
        try {
            return this.objectMapper.readValue(jsonStr, new com.fasterxml.jackson.core.type.TypeReference<Map<K, V>>() {
                @Override
                public Type getType() {
                    return typeReference.getType();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <K, V> Map<K, V> fromJsonMap(InputStream jsonIn, TypeReference<Map<K, V>> typeReference) {
        try {
            return this.objectMapper.readValue(jsonIn, new com.fasterxml.jackson.core.type.TypeReference<Map<K, V>>() {
                @Override
                public Type getType() {
                    return typeReference.getType();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
