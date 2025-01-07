package com.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class ConfigManager {
    private static Properties properties = new Properties();
    private static final Logger logger = Logger.getLogger(ConfigManager.class.getName());

    static {
        try {
            loadMainConfig();
            loadPlatformSpecificConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadMainConfig() throws IOException {
        logger.info("Loading generic config.properties...");
        try (FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties")) {
            properties.load(fis);
        }catch  (IOException e) {
            logger.severe("Failed to load configuration files: " + e.getMessage());
        }

    }

    private static void loadPlatformSpecificConfig() throws IOException {
       String platform = properties.getProperty("env", "web");
        String configFilePath;

        switch (platform.toLowerCase()) {
            case "api":
                configFilePath = "src/test/resources/config/api-config.properties";
                break;
            case "mobile":
                configFilePath = "src/test/resources/config/mobile-config.properties";
                break;
            default:
                configFilePath = "src/test/resources/config/web-config.properties";
                break;
        }

        logger.info("Loading platform-specific configuration: " + configFilePath);
        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            properties.load(fis);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            logger.warning("Property not found: " + key);
        }
        return value;
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
