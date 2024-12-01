package com.a101.mobile.core.driver;

import com.a101.mobile.core.config.FrameworkConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import lombok.extern.log4j.Log4j2;

import java.net.URL;

@Log4j2
public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();
    private static final FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);
    private static final CapabilityManager capabilityManager = new CapabilityManager();

    private DriverManager() {
        // Private constructor to prevent instantiation
    }

    public static void initDriver() {
        if (getDriver() == null) {
            try {
                URL appiumServerUrl = new URL(config.appiumServerUrl());
                DesiredCapabilities capabilities = capabilityManager.getCapabilities();

                AppiumDriver driver = createDriver(appiumServerUrl, capabilities);
                setDriver(driver);

                configureDriver(driver);
                log.info("Driver initialized successfully for platform: {}", config.platform());
            } catch (Exception e) {
                log.error("Failed to initialize driver: {}", e.getMessage());
                throw new RuntimeException("Driver initialization failed", e);
            }
        }
    }

    private static AppiumDriver createDriver(URL appiumServerUrl, DesiredCapabilities capabilities) {
        return config.platform().equalsIgnoreCase("android") ?
                new AndroidDriver(appiumServerUrl, capabilities) :
                new IOSDriver(appiumServerUrl, capabilities);
    }

    private static void configureDriver(AppiumDriver driver) {
        if (driver != null) {
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(config.implicitWait()));
        }
    }

    public static AppiumDriver getDriver() {
        return driverThreadLocal.get();
    }

    private static void setDriver(AppiumDriver driver) {
        driverThreadLocal.set(driver);
    }

    public static void quitDriver() {
        try {
            AppiumDriver driver = getDriver();
            if (driver != null) {
                driver.quit();
                driverThreadLocal.remove();
                log.info("Driver quit successfully");
            }
        } catch (Exception e) {
            log.error("Error while quitting driver: {}", e.getMessage());
        }
    }

    public static void resetApp() {
        try {
            AppiumDriver driver = getDriver();
            if (driver != null) {
                driver.resetApp();
                log.info("App reset successfully");
            }
        } catch (Exception e) {
            log.error("Error while resetting app: {}", e.getMessage());
        }
    }

    public static boolean isDriverInitialized() {
        return getDriver() != null;
    }

    public static String getPlatform() {
        return config.platform();
    }
}
