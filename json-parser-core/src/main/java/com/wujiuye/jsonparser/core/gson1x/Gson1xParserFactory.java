package com.wujiuye.jsonparser.core.gson1x;

import com.google.gson.GsonBuilder;
import com.wujiuye.jsonparser.core.AbstractJsonParserFactory;
import com.wujiuye.jsonparser.core.SerializeConfig;
import com.wujiuye.jsonparser.core.gson.*;

import java.util.Date;

/**
 * @author wujiuye 2021/06/08
 */
public class Gson1xParserFactory extends AbstractJsonParserFactory<GsonParser> {

    public Gson1xParserFactory(SerializeConfig config) {
        super(config);
    }

    @Override
    protected GsonParser newJsonParser(SerializeConfig config) {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateTypeAdapter(config.getDateFormat()))
                .setExclusionStrategies(new GsonExclusionStrategy());
        if (config.isSerializeNulls()) {
            gsonBuilder.serializeNulls();
        }
        return new GsonParser(gsonBuilder.create());
    }

    @Override
    protected void customizer(GsonParser parser, Class<?> tClass) {

    }

}
