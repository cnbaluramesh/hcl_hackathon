package com.hcl.hackaton.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ErrorMessageUtil {

    private static final Properties properties = new Properties();

    static {
        try (InputStream inputStream = ErrorMessageUtil.class.getClassLoader().getResourceAsStream("error_messages.properties")) {
            if (inputStream == null) {
                throw new IOException("Error messages file not found");
            }
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Failed to load error messages");
        }
    }

    public static String getErrorMessage(String errorCode) {
        return properties.getProperty(errorCode, "Unknown error code");
    }
}
