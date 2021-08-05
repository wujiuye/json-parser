package com.wujiuye.jsonparser.core.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * 解决转意问题
 *
 * @author wujiuye 2020/09/14
 */
public class StringTypeAdapter extends TypeAdapter<String> {

    public StringTypeAdapter() {
    }

    @Override
    public void write(JsonWriter out, String value) throws IOException {
        // 去掉转译字符
        out.setHtmlSafe(false);
        out.value(value);
    }

    @Override
    public String read(JsonReader in) throws IOException {
        return in.nextString();
    }

}
