package com.wujiuye.jsonparser.core.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.wujiuye.jsonparser.core.util.DateFormatUtils;
import com.wujiuye.jsonparser.core.util.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * LocalDate日期类型的转换
 *
 * @author wujiuye 2021/06/08
 */
public class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {

    private int zone;
    private DateTimeFormatter serializerFormatter;

    public LocalDateTypeAdapter(int zone, String serializerDatePattern) {
        this.zone = zone;
        if (StringUtils.isNullOrEmpty(serializerDatePattern)) {
            serializerFormatter = null;
            return;
        }
        this.serializerFormatter = DateTimeFormatter.ofPattern(serializerDatePattern);
    }

    @Override
    public void write(JsonWriter jsonWriter, LocalDate time) throws IOException {
        if (serializerFormatter == null) {
            jsonWriter.value(LocalDateTime.of(time, LocalTime.of(0, 0, 0))
                    .toInstant(ZoneOffset.ofHours(zone)).toEpochMilli());
        } else {
            jsonWriter.value(time.format(serializerFormatter));
        }
    }

    @Override
    public LocalDate read(JsonReader jsonReader) throws IOException {
        String datetime = jsonReader.nextString();
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
