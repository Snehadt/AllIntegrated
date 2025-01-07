package com.utilHandler;

import com.config.ConfigManager;

import java.io.IOException;
import java.util.Base64;

public class Decoders {
    public static String propertiesDecode(String key) throws IOException {
        String  encodedMsg = ConfigManager.getProperty(key);
        byte[] decoded_byte = Base64.getDecoder().decode(encodedMsg);
        return new String(decoded_byte);
    }
}
