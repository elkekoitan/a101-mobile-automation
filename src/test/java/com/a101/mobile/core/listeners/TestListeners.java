package com.a101.mobile.core.listeners;

import com.a101.mobile.core.reporting.ExtentReportManager;
import com.a101.mobile.core.utils.ScreenshotUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TestListeners implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
        log.info("Starting test suite: {}", context.getName());
        ExtentReportManager.initReports();
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("Finishing test suite: {}", context.getName());
        ExtentReportManager.flushReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Starting test: {}", result.getName());
        ExtentReportManager.createTest(result.getName(), result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test passed: {}", result.getName());
        ExtentReportManager.logPass(result.getName() + " - Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test failed: {}", result.getName());
        ExtentReportManager.logFail(result.getName() + " - Test Failed");
        ExtentReportManager.logFail(result.getThrowable().getMessage());

        String base64Screenshot = ScreenshotUtils.captureScreenshotAsBase64();
        ExtentReportManager.addScreenshot(base64Screenshot);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test skipped: {}", result.getName());
        ExtentReportManager.logSkip(result.getName() + " - Test Skipped");
        ExtentReportManager.logSkip(result.getThrowable().getMessage());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.warn("Test failed within success percentage: {}", result.getName());
        ExtentReportManager.logWarning(result.getName() + " - Test Failed Within Success Percentage");
    }
}
