package com.wujiuye.jsonparser.core.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.wujiuye.jsonparser.core.AbstractJsonParserFactory;
import com.wujiuye.jsonparser.core.SerializeConfig;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author wujiuye 2021/06/08
 */
public class JacksonParserFactory extends AbstractJsonParserFactory<JacksonParser> {

    public JacksonParserFactory(SerializeConfig config) {
        super(config);
    }

    @Override
    protected JacksonParser newJsonParser(SerializeConfig config) {
        ObjectMapperSub objectMapper = createObjectMapper(config);
        return new JacksonParser(objectMapper);
    }

    @Override
    protected void customizer(JacksonParser parser, Class<?> tClass) {
        JacksonExclusionStrategy strategy = JacksonExclusionStrategy.getInstance(tClass);
        if (strategy != null) {
            ((ObjectMapperSub) parser.getObjectMapper()).putStrategy(tClass, strategy);
        }
    }

    private static ObjectMapperSub createObjectMapper(SerializeConfig config) {
        ObjectMapperSub objectMapper = new ObjectMapperSub();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        if (!config.isSerializeNulls()) {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
        SimpleModule timeModule = new SimpleModule();
        // 序列化配置
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(config.getTimezone(),
                config.getLocalDateTimeFormat()));
        timeModule.addSerializer(LocalDate.class, new LocalDateSerializer(config.getTimezone(),
                config.getLocalDateFormat()));
        timeModule.addSerializer(Date.class, new DateSerializer(config.getDateFormat()));
        timeModule.addSerializer(Double.class, new DoubleSerializer());
        timeModule.addSerializer(String.class, new StringSerializer());
        // 反序列化配置
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(config.getTimezone()));
        timeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(config.getTimezone()));
        timeModule.addDeserializer(Date.class, new DateDeserializer());
        timeModule.addDeserializer(Double.class, new DoubleDeserializer());
        timeModule.addDeserializer(String.class, new StringDeserializer(config.isOpenXssFilter()));
        objectMapper.registerModule(timeModule);
        return objectMapper;
    }

}
