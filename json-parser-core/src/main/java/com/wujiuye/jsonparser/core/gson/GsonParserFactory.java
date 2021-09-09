package com.wujiuye.jsonparser.core.gson;

import com.google.gson.GsonBuilder;
import com.wujiuye.jsonparser.core.AbstractJsonParserFactory;
import com.wujiuye.jsonparser.core.SerializeConfig;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author wujiuye 2021/06/08
 */
public class GsonParserFactory extends AbstractJsonParserFactory<GsonParser> {

    public GsonParserFactory(SerializeConfig config) {
        super(config);
    }

    @Override
    protected GsonParser newJsonParser(SerializeConfig config) {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateTypeAdapter(config.getDateFormat()))
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter(config.getTimezone(),
                        config.getLocalDateTimeFormat()))
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter(config.getTimezone(),
                        config.getLocalDateFormat()))
                .registerTypeAdapter(Double.class, new DoubleTypeAdapter())
                .registerTypeAdapter(String.class, new StringTypeAdapter(config.isOpenXssFilter()))
                .addDeserializationExclusionStrategy(new GsonExclusionStrategy());
        if (config.isSerializeNulls()) {
            gsonBuilder.serializeNulls();
        }
        return new GsonParser(gsonBuilder.create());
    }

    @Override
    protected void customizer(GsonParser parser, Class<?> tClass) {

    }

}
