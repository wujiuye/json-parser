package com.wujiuye.jsonparser.core.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wujiuye 2020/09/09
 */
public class ObjectMapperSub extends ObjectMapper {

    private Set<Class<?>> strategys = new HashSet<>();

    public void putStrategy(final Class<?> cla, JacksonExclusionStrategy strategy) {
        if (strategys.contains(cla)) {
            return;
        }
        synchronized (this) {
            if (strategys.contains(cla)) {
                return;
            }
            Set<Class<?>> newSet = new HashSet<>();
            newSet.add(cla);
            strategys = newSet;
            this.setFilters(strategy);
            // 将@JsonFilter 作用于 java 对象上
            this.addMixInAnnotations(cla, JacksonExclusionStrategy.MyFilter.class);
        }
    }

}
