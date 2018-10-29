package com.endofmaster.commons.util.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * @author YQ.Huang
 * @update ZM.Wang
 */
public abstract class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T fromJson(String json, Class<T> tClass) throws JsonException {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

    public static String toJson(Object object) throws JsonException {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new JsonException(e);
        }
    }

    public static JsonNode toJsonNode(String json) throws IOException {
        return objectMapper.readTree(json);
    }

    public static boolean isJson(String json) {
        if (StringUtils.isBlank(json)) {
            return false;
        }
        try {
            objectMapper.readTree(json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
