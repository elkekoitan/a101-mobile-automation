package com.a101.mobile.core.utils;

import java.util.Map;
import java.util.HashMap;
import org.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DataUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private DataUtils() {
        // Private constructor
    }

    public static Map<String, Object> convertJsonToMap(String jsonStr) {
        try {
            return objectMapper.readValue(jsonStr, HashMap.class);
        } catch (Exception e) {
            log.error("Error converting JSON to Map: {}", e.getMessage());
            return new HashMap<>();
        }
    }

    public static String convertMapToJson(Map<String, Object> map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            log.error("Error converting Map to JSON: {}", e.getMessage());
            return "{}";
        }
    }

    public static <T> T convertJsonToObject(String jsonStr, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonStr, clazz);
        } catch (Exception e) {
            log.error("Error converting JSON to Object: {}", e.getMessage());
            return null;
        }
    }

    public static String convertObjectToJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("Error converting Object to JSON: {}", e.getMessage());
            return "{}";
        }
    }

    public static boolean isValidJson(String jsonStr) {
        try {
            new JSONObject(jsonStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getValueFromJson(String jsonStr, String key) {
        try {
            JSONObject json = new JSONObject(jsonStr);
            return json.optString(key, "");
        } catch (Exception e) {
            log.error("Error getting value from JSON: {}", e.getMessage());
            return "";
        }
    }

    public static Map<String, Object> getMobileTestData() {
        Map<String, Object> testData = new HashMap<>();
        testData.put("phoneNumber", "5545417561");
        testData.put("otp", "141414");
        testData.put("testEmail", "test@a101.com");
        testData.put("testPassword", "Test123!");
        return testData;
    }
}
