package com.a101.mobile.core.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class ExtentReportManager {
    private static ExtentReports extent;
    private static final Map<Long, ExtentTest> testMap = new HashMap<>();
    private static final String REPORT_PATH = "test-output/ExtentReport/";
    private static final String REPORT_FILE = "AutomationReport.html";

    private ExtentReportManager() {
        // Private constructor
    }

    public static synchronized void initReports() {
        if (extent == null) {
            // Create report directory if it doesn't exist
            File directory = new File(REPORT_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_PATH + REPORT_FILE);
            configureReporter(sparkReporter);

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            setSystemInfo();

            log.info("Extent Reports initialized successfully");
        }
    }

    private static void configureReporter(ExtentSparkReporter sparkReporter) {
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("A101 Mobile Automation Report");
        sparkReporter.config().setReportName("Automation Test Results");
        sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        sparkReporter.config().setEncoding("UTF-8");
    }

    private static void setSystemInfo() {
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Environment", System.getProperty("environment", "QA"));
        extent.setSystemInfo("Platform", System.getProperty("platform", "Android"));
    }

    public static synchronized ExtentTest createTest(String testName, String description) {
        ExtentTest test = extent.createTest(testName, description);
        testMap.put(Thread.currentThread().getId(), test);
        log.debug("Created test in Extent Report: {}", testName);
        return test;
    }

    public static synchronized ExtentTest getTest() {
        return testMap.get(Thread.currentThread().getId());
    }

    public static void logInfo(String message) {
        getTest().log(Status.INFO, message);
        log.info(message);
    }

    public static void logPass(String message) {
        getTest().log(Status.PASS, message);
        log.info("PASSED: " + message);
    }

    public static void logFail(String message) {
        getTest().log(Status.FAIL, message);
        log.error("FAILED: " + message);
    }

    public static void logSkip(String message) {
        getTest().log(Status.SKIP, message);
        log.warn("SKIPPED: " + message);
    }

    public static void logWarning(String message) {
        getTest().log(Status.WARNING, message);
        log.warn(message);
    }

    public static void addScreenshot(String base64Screenshot) {
        getTest().addScreenCaptureFromBase64String(base64Screenshot);
    }

    public static synchronized void flushReports() {
        if (extent != null) {
            extent.flush();
            log.info("Extent Reports flushed successfully");
        }
    }

    public static void removeTest() {
        testMap.remove(Thread.currentThread().getId());
    }
}
