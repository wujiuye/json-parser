package com.wujiuye.jsonparser.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wujiuye.jsonparser.core.util.XssFilterUtils;

import java.io.IOException;

/**
 * 字符串序列化
 * 解决'='这类特殊符号转意问题
 *
 * @author wujiuye 2020/09/14
 */
class StringSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(s);
    }

}

/**
 * 字符串反序列化
 *
 * @author wujiuye 2020/09/14
 */
class StringDeserializer extends JsonDeserializer<String> {

    private boolean openXssFilter;

    public StringDeserializer(boolean openXssFilter) {
        this.openXssFilter = openXssFilter;
    }

    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String value = jsonParser.getText();
        if (!openXssFilter) {
            return value;
        }
        return XssFilterUtils.xssFilter(value);
    }

}