package com.xiya.demo.util;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * JSON 工具类
 */
public class JSONUtil {

    /**
     * 通用 Gson
     */
    private static final Gson GSON = new GsonBuilder().serializeNulls().create();


    public static String toJson(Object object) {
        return GSON.toJson(object);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return GSON.fromJson(json, typeOfT);
    }

    public static <T> T fromJson(String json, Class<T> cls) {
        return GSON.fromJson(json, cls);
    }
}
