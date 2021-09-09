package com.wujiuye.jsonparser.core.gson1x;

import com.google.gson.*;
import com.wujiuye.jsonparser.core.util.DateFormatException;
import com.wujiuye.jsonparser.core.util.DateFormatUtils;
import com.wujiuye.jsonparser.core.util.DateUtils;
import com.wujiuye.jsonparser.core.util.StringUtils;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {

    private final String serializerDatePattern;

    public DateTypeAdapter(String serializerDatePattern) {
        this.serializerDatePattern = serializerDatePattern;
    }

    @Override
    public JsonElement serialize(Date o, Type type, JsonSerializationContext jsonSerializationContext) {
        Object value;
        if (serializerDatePattern == null) {
            value = o.getTime();
        } else {
            value = DateUtils.dateToString(o, serializerDatePattern);
        }
        return jsonSerializationContext.serialize(value);
    }

    @Override
    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String datetime = jsonElement.getAsString();
        if (StringUtils.isNumber(datetime)) {
            if (datetime.length() == 10) {
                return new Date(Long.parseLong(datetime) * 1000);
            } else {
                return new Date(Long.parseLong(datetime));
            }
        }
        try {
            SimpleDateFormat sdf = DateFormatUtils.chooseSimpleDateFormat(datetime);
            return sdf.parse(datetime);
        } catch (ParseException e) {
            throw new DateFormatException(datetime);
        }
    }

}
