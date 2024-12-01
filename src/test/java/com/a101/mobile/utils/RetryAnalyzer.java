package com.a101.mobile.core.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.aeonbits.owner.ConfigFactory;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class RetryAnalyzer implements IRetryAnalyzer {
    private int retryCount = 0;
    private static final FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);

    @Override
    public boolean retry(ITestResult result) {
        int maxRetry = config.retryCount();
        if (retryCount < maxRetry) {
            log.info("Retrying test '{}' for the {} time(s).",
                    result.getName(), retryCount + 1);
            retryCount++;
            return true;
        }
        log.info("Max retry count {} reached for test '{}'",
                maxRetry, result.getName());
        return false;
    }
}
