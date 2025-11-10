package org.trello;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("=== PASSED TEST: {} ===", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("=== FAILED TEST: {} ===", result.getName(), result.getThrowable());
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("=== STARTED TEST: {} ===", result.getName());
    }
}

