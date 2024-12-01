package com.a101.mobile.core;

import com.a101.mobile.core.config.FrameworkConfig;
import com.a101.mobile.core.driver.DriverManager;
import com.a101.mobile.core.reporting.ExtentReportManager;
import com.a101.mobile.core.reporting.VideoRecorder;
import com.a101.mobile.core.utils.GestureUtils;
import com.a101.mobile.core.utils.ScreenshotUtils;
import com.a101.mobile.core.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Attachment;
import org.aeonbits.owner.ConfigFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class BaseTest {
    protected AppiumDriver driver;
    protected WaitUtils waitUtils;
    protected GestureUtils gestureUtils;
    protected FrameworkConfig config;
    private VideoRecorder videoRecorder;
    private static final String TEST_EVIDENCE_DIR = "test-output/evidence/";
    private static final ThreadLocal<String> testName = new ThreadLocal<>();

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        log.info("Starting test suite execution");
        ExtentReportManager.initReports();
        new File(TEST_EVIDENCE_DIR).mkdirs();
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        log.info("Setting up test: {}", method.getName());
        testName.set(method.getName());

        try {
            config = ConfigFactory.create(FrameworkConfig.class);
            DriverManager.initDriver();
            driver = DriverManager.getDriver();

            waitUtils = new WaitUtils(driver, config.explicitWait());
            gestureUtils = new GestureUtils(driver);

            if (config.isVideoRecordingEnabled()) {
                videoRecorder = new VideoRecorder();
                videoRecorder.startRecording(testName.get());
            }

            log.info("Test setup completed successfully");
        } catch (Exception e) {
            log.error("Test setup failed: {}", e.getMessage());
            throw new RuntimeException("Failed to setup test", e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        log.info("Tearing down test: {}", testName.get());
        try {
            if (!result.isSuccess()) {
                captureFailureEvidence(result);
            }

            if (videoRecorder != null) {
                videoRecorder.stopRecording();
            }

            DriverManager.quitDriver();
            log.info("Test teardown completed successfully");
        } catch (Exception e) {
            log.error("Test teardown failed: {}", e.getMessage());
        }
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        ExtentReportManager.flushReports();
        log.info("Test suite execution completed");
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    protected byte[] captureScreenshot() {
        return ScreenshotUtils.captureScreenshotAsBytes(driver);
    }

    @Attachment(value = "{0}", type = "text/plain")
    protected String saveTextLog(String message) {
        return message;
    }

    private void captureFailureEvidence(ITestResult result) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String testMethodName = result.getMethod().getMethodName();
        String screenshotPath = String.format("%s/%s_%s.png", TEST_EVIDENCE_DIR, testMethodName, timestamp);

        try {
            // Capture screenshot
            ScreenshotUtils.captureScreenshot(driver, screenshotPath);

            // Attach to Allure report
            captureScreenshot();

            // Add to Extent Report
            ExtentReportManager.addScreenshotToReport(testMethodName, screenshotPath);

            // Log the failure
            String failureLog = String.format("Test failed: %s\nError: %s",
                    testMethodName,
                    result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown error");
            saveTextLog(failureLog);

            log.info("Failure evidence captured for test: {}", testMethodName);
        } catch (Exception e) {
            log.error("Failed to capture failure evidence: {}", e.getMessage());
        }
    }

    protected void waitForPageLoad() {
        try {
            waitUtils.waitForPageToLoad();
            log.debug("Page load completed");
        } catch (Exception e) {
            log.error("Page load wait failed: {}", e.getMessage());
            throw e;
        }
    }

    protected void handleAlert() {
        try {
            if (driver.switchTo().alert() != null) {
                driver.switchTo().alert().accept();
                log.debug("Alert handled successfully");
            }
        } catch (Exception e) {
            log.debug("No alert present to handle");
        }
    }

    protected void refreshPage() {
        try {
            driver.navigate().refresh();
            waitForPageLoad();
            log.debug("Page refreshed successfully");
        } catch (Exception e) {
            log.error("Failed to refresh page: {}", e.getMessage());
            throw e;
        }
    }

    protected void scrollToElement(String elementText) {
        try {
            gestureUtils.scrollToText(elementText);
            log.debug("Scrolled to element with text: {}", elementText);
        } catch (Exception e) {
            log.error("Failed to scroll to element: {}", e.getMessage());
            throw e;
        }
    }
}
