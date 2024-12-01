package com.a101.mobile.core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ConfigManager {
    private static final Properties props = new Properties();
    private static final String DEFAULT_PROPERTIES = "env/default/default.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try {
            // Load default properties
            props.load(new FileInputStream(DEFAULT_PROPERTIES));

            // Load environment specific properties if any
            String env = System.getProperty("env", "default");
            if (!env.equals("default")) {
                String envProperties = String.format("env/%s/env.properties", env);
                props.load(new FileInputStream(envProperties));
            }

            log.info("Properties loaded successfully for environment: {}", env);
        } catch (IOException e) {
            log.error("Error loading properties: {}", e.getMessage());
            throw new RuntimeException("Failed to load properties", e);
        }
    }

    public static String getProperty(String key) {
        String value = props.getProperty(key);
        if (value == null) {
            log.warn("Property not found: {}", key);
            return "";
        }
        return value;
    }

    public static String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key, "0"));
    }
}
