package com.xiya.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

public class JsonUtils {
    
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        // 序列化时，忽略值为null的属性
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 序列化时，忽略空的bean(即沒有任何Field)
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 反序列化时，忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // make all member fields serializable without further annotations,
        // instead of just public fields (default setting).
        // mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
    }

    public static String toJson(Object object) throws IOException {
        return mapper.writeValueAsString(object);
    }
    
    public static <T> T fromJson(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) throws IOException {
        return mapper.readValue(json, typeReference);
    }
}
