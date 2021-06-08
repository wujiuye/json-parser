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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * LocalDate序列化
 *
 * @author wujiuye 2021/06/08
 */
class LocalDateSerializer extends JsonSerializer<LocalDate> {

    private int zone;
    private DateTimeFormatter formatter;

    public LocalDateSerializer(int zone, String datePattern) {
        this.zone = zone;
        if (datePattern == null) {
            this.formatter = null;
            return;
        }
        this.formatter = DateTimeFormatter.ofPattern(datePattern);
    }

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (formatter == null) {
            jsonGenerator.writeNumber(LocalDateTime.of(localDate, LocalTime.of(0, 0, 0))
                    .toInstant(ZoneOffset.ofHours(zone)).toEpochMilli());
        } else {
            jsonGenerator.writeString(localDate.format(formatter));
        }
    }

}

/**
 * LocalDate反序列化
 *
 * @author wujiuye 2021/06/08
 */
class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    private int zone;

    public LocalDateDeserializer(int zone) {
        this.zone = zone;
    }

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String datetime = jsonParser.getText();
        if (StringUtils.isNumber(datetime)) {
            long times = Long.parseLong(datetime);
            if (datetime.length() == 13) {
                times /= 1000;
            }
            return LocalDateTime.ofEpochSecond(times, 0, ZoneOffset.ofHours(zone)).toLocalDate();
        } else {
            DateTimeFormatter formatter = DateFormatUtils.chooseDateTimeFormatter(datetime);
            return LocalDate.parse(datetime, formatter);
        }
    }

}