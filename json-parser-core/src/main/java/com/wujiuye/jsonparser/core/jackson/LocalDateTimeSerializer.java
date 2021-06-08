package com.wujiuye.jsonparser.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.wujiuye.jsonparser.core.util.DateFormatUtils;
import com.wujiuye.jsonparser.core.util.StringUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * LocalDateTime序列化
 *
 * @author wujiuye 2020/05/08
 */
class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    private int zone;
    private DateTimeFormatter formatter;

    public LocalDateTimeSerializer(int zone, String datePattern) {
        this.zone = zone;
        if (datePattern == null) {
            this.formatter = null;
            return;
        }
        this.formatter = DateTimeFormatter.ofPattern(datePattern);
    }

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (formatter == null) {
            jsonGenerator.writeNumber(localDateTime.toInstant(ZoneOffset.ofHours(zone)).toEpochMilli());
        } else {
            jsonGenerator.writeString(localDateTime.format(formatter));
        }
    }

}

/**
 * LocalDateTime反序列化
 *
 * @author wujiuye 2020/05/08
 */
class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private int zone;

    public LocalDateTimeDeserializer(int zone) {
        this.zone = zone;
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String datetime = jsonParser.getText();
        if (StringUtils.isNumber(datetime)) {
            long times = Long.parseLong(datetime);
            if (datetime.length() == 13) {
                times /= 1000;
            }
            return LocalDateTime.ofEpochSecond(times, 0, ZoneOffset.ofHours(zone));
        } else {
            DateTimeFormatter formatter = DateFormatUtils.chooseDateTimeFormatter(datetime);
            return LocalDateTime.parse(datetime, formatter);
        }
    }

}