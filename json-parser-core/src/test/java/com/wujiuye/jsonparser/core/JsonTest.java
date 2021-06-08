package com.wujiuye.jsonparser.core;

import org.junit.Test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class JsonTest {

    public static class Object1 {
        private transient String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Object2 extends Object1 {

    }

    @Test
    public void testGsonMap() {
        // gson bug
        Map<String, Object> data = new HashMap<String, Object>() {{
            put("xxx", "yyy");
        }};
        System.out.println(JsonUtils.toJsonString(data));

        HashMap<String, Object> data2 = new HashMap<>();
        data2.put("xxx", new JsonTestModel().setId(10));
        System.out.println(JsonUtils.toJsonString(data2));

        String v = JsonUtils.toJsonString(data2);
        Map<String, JsonTestModel> v2 = JsonUtils.fromJsonMap(v, new TypeReference<Map<String, JsonTestModel>>() {
            @Override
            public Type getType() {
                return super.getType();
            }
        });
        System.out.println(v2.get("xxx"));

        Object1 object = new Object2();
        object.setName("xxxxx");
        System.out.println(JsonUtils.toJsonString(object));
    }

}
