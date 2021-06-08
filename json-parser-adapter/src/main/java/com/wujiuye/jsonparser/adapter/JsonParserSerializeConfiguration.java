package com.wujiuye.jsonparser.adapter;

import com.wujiuye.jsonparser.core.JsonUtils;
import com.wujiuye.jsonparser.core.SerializeConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 序列化配置
 *
 * @author wujiuye 2021/05/10
 */
@Configuration
public class JsonParserSerializeConfiguration implements InitializingBean {

    @Value("${spring.json-parser.serialize-null:false}")
    private boolean serializeNulls;
    @Value("${spring.json-parser.date-format:yyyy-MM-dd HH:mm:ss}")
    private String dateFormat;
    @Value("${spring.json-parser.local-date-format:yyyy-MM-dd}")
    private String localDateFormat;
    @Value("${spring.json-parser.local-date-time-format:yyyy-MM-dd HH:mm:ss}")
    private String localDateTimeFormat;
    @Value("${spring.json-parser.timezone:8}")
    private Integer timezone;
    @Value("${spring.json-parser.open-xss-filter:true}")
    private Boolean openXssFilter;

    @Override
    public void afterPropertiesSet() {
        SerializeConfig serializeConfig = new SerializeConfig();
        serializeConfig.setSerializeNulls(serializeNulls);
        serializeConfig.setTimezone(timezone);
        serializeConfig.setDateFormat(dateFormat);
        serializeConfig.setLocalDateFormat(localDateFormat);
        serializeConfig.setLocalDateTimeFormat(localDateTimeFormat);
        serializeConfig.setOpenXssFilter(openXssFilter);
        JsonUtils.resetSerializeConfig(serializeConfig);
    }

}
