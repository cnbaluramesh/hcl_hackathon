package com.hcl.hackaton.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class LoggerUtil {

    private static final Logger logger = LoggerFactory.getLogger(LoggerUtil.class);

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    public void logInfo(Class<?> clazz, String message) {
        executorService.submit(() -> {
            Logger classLogger = LoggerFactory.getLogger(clazz);
            classLogger.info(message);
        });
    }
    public void logDebug(Class<?> clazz, String message) {
        executorService.submit(() -> {
            Logger classLogger = LoggerFactory.getLogger(clazz);
            classLogger.debug(message);
        });
    }

    public void logError(Class<?> clazz, String message, Throwable throwable) {
        executorService.submit(() -> {
            Logger classLogger = LoggerFactory.getLogger(clazz);
            classLogger.error(message, throwable);
        });
    }

    public void shutDown() {
        executorService.shutdown();
    }
}