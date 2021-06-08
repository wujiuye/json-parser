package com.wujiuye.jsonparser.core;

/**
 * @author wujiuye 2021/06/08
 */
public abstract class AbstractJsonParserFactory<T extends JsonParser> {

    private final SerializeConfig config;
    private final T jsonParser;

    public AbstractJsonParserFactory(SerializeConfig config) {
        this.config = config;
        this.jsonParser = newJsonParser(config);
    }

    public T getJsonParser(Class<?> tClass) {
        T parser = newJsonParser(config);
        customizer(parser, tClass);
        return parser;
    }

    public T getJsonParser() {
        return this.jsonParser;
    }

    protected abstract T newJsonParser(SerializeConfig config);

    protected abstract void customizer(T parser, Class<?> tClass);

}
