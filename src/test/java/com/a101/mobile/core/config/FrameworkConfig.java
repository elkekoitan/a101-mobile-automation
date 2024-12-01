package com.a101.mobile.core.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.LoadPolicy;
import org.aeonbits.owner.Config.LoadType;
import org.aeonbits.owner.Config.Sources;

@LoadPolicy(LoadType.MERGE)
@Sources({
        "system:properties",
        "classpath:config/${env}.properties",
        "classpath:config/default.properties"
})
public interface FrameworkConfig extends Config {

    @Key("appium.server.url")
    @DefaultValue("http://localhost:4723/wd/hub")
    String appiumServerUrl();

    @Key("platform")
    @DefaultValue("android")
    String platform();

    @Key("implicit.wait")
    @DefaultValue("10")
    int implicitWait();

    @Key("explicit.wait")
    @DefaultValue("15")
    int explicitWait();

    @Key("polling.time")
    @DefaultValue("1")
    int pollingTime();

    // Android Capabilities
    @Key("android.platform.version")
    @DefaultValue("11.0")
    String androidPlatformVersion();

    @Key("android.device.name")
    @DefaultValue("Pixel_4_API_30")
    String androidDeviceName();

    @Key("android.automation.name")
    @DefaultValue("UiAutomator2")
    String androidAutomationName();

    @Key("android.app.package")
    @DefaultValue("com.a101.mobile")
    String androidAppPackage();

    @Key("android.app.activity")
    @DefaultValue("com.a101.mobile.MainActivity")
    String androidAppActivity();

    // iOS Capabilities
    @Key("ios.platform.version")
    @DefaultValue("15.0")
    String iosPlatformVersion();

    @Key("ios.device.name")
    @DefaultValue("iPhone 13")
    String iosDeviceName();

    @Key("ios.automation.name")
    @DefaultValue("XCUITest")
    String iosAutomationName();

    @Key("ios.bundle.id")
    @DefaultValue("com.a101.mobile")
    String iosBundleId();

    // Framework Settings
    @Key("screenshot.on.failure")
    @DefaultValue("true")
    boolean screenshotOnFailure();

    @Key("video.recording.enabled")
    @DefaultValue("true")
    boolean isVideoRecordingEnabled();

    @Key("retry.count")
    @DefaultValue("3")
    int retryCount();

    @Key("parallel.thread.count")
    @DefaultValue("3")
    int parallelThreadCount();

    // Test Data Settings
    @Key("test.data.path")
    @DefaultValue("src/test/resources/testdata")
    String testDataPath();

    // Reporting Settings
    @Key("extent.report.path")
    @DefaultValue("test-output/ExtentReports")
    String extentReportPath();

    @Key("allure.report.path")
    @DefaultValue("test-output/AllureReports")
    String allureReportPath();

    // Environment Settings
    @Key("env")
    @DefaultValue("qa")
    String environment();

    @Key("api.base.url")
    String apiBaseUrl();
}
