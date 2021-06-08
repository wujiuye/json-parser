package com.wujiuye.jsonparser.core;

/**
 * 序列化配置
 *
 * @author wujiuye 2021/05/10
 */
public class SerializeConfig {

    /**
     * 是否序列化null字段
     */
    private boolean serializeNulls = false;
    /**
     * 是否开启防xss攻击过滤
     */
    private boolean openXssFilter = true;
    /**
     * 使用的时区
     */
    private int timezone = 8;
    /**
     * Date类型序列化格式，配置为null则序列化为时间戳（反序列化时也会自动适配时间戳）
     */
    private String dateFormat = "yyyy-MM-dd HH:mm:ss";
    /**
     * LocalDateTime类型序列化格式，配置为null则序列化为时间戳（反序列化时也会自动适配时间戳）
     */
    private String localDateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    /**
     * LocalDate类型序列化格式，配置为null则序列化为时间戳（反序列化时也会自动适配时间戳）
     */
    private String localDateFormat = "yyyy-MM-dd";

    public boolean isSerializeNulls() {
        return serializeNulls;
    }

    public void setSerializeNulls(boolean serializeNulls) {
        this.serializeNulls = serializeNulls;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public String getLocalDateTimeFormat() {
        return localDateTimeFormat;
    }

    public void setLocalDateTimeFormat(String localDateTimeFormat) {
        this.localDateTimeFormat = localDateTimeFormat;
    }

    public String getLocalDateFormat() {
        return localDateFormat;
    }

    public void setLocalDateFormat(String localDateFormat) {
        this.localDateFormat = localDateFormat;
    }

    public void setOpenXssFilter(boolean openXssFilter) {
        this.openXssFilter = openXssFilter;
    }

    public boolean isOpenXssFilter() {
        return openXssFilter;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

}

