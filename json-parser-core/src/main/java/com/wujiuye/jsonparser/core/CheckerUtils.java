package com.wujiuye.jsonparser.core;

/**
 * @author wujiuye
 * @version 2021/09/09
 */
class CheckerUtils {

    public static boolean isUseJackson(ClassLoader classLoader) {
        try {
            classLoader.loadClass("com.fasterxml.jackson.databind.ObjectMapper");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static boolean isUseGson(ClassLoader classLoader) {
        try {
            classLoader.loadClass("com.google.gson.Gson");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static int getGsonVersion(ClassLoader classLoader) {
        try {
            Class<?> tClass = classLoader.loadClass("com.google.gson.TypeAdapter");
            if (tClass.isInterface()) {
                return 1;
            }
            return 2;
        } catch (ClassNotFoundException e) {
            return 1;
        }
    }

}
