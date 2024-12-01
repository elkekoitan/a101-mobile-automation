package com.a101.mobile.core.driver;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CapabilityManager {
    private final FrameworkConfig config = ConfigFactory.create(FrameworkConfig.class);

    public DesiredCapabilities getCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        try {
            if (config.platform().equalsIgnoreCase("android")) {
                setAndroidCapabilities(capabilities);
            } else if (config.platform().equalsIgnoreCase("ios")) {
                setIOSCapabilities(capabilities);
            } else {
                throw new IllegalArgumentException("Invalid platform specified: " + config.platform());
            }

            setCommonCapabilities(capabilities);
            log.info("Capabilities set successfully for platform: {}", config.platform());
            return capabilities;
        } catch (Exception e) {
            log.error("Failed to set capabilities: {}", e.getMessage());
            throw new RuntimeException("Failed to set capabilities", e);
        }
    }

    private void setCommonCapabilities(DesiredCapabilities capabilities) {
        capabilities.setCapability("noReset", false);
        capabilities.setCapability("fullReset", false);
        capabilities.setCapability("newCommandTimeout", 300);
        capabilities.setCapability("autoGrantPermissions", true);
    }

    private void setAndroidCapabilities(DesiredCapabilities capabilities) {
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("automationName", config.androidAutomationName());
        capabilities.setCapability("deviceName", config.androidDeviceName());
        capabilities.setCapability("platformVersion", config.androidPlatformVersion());
        capabilities.setCapability("appPackage", config.androidAppPackage());
        capabilities.setCapability("appActivity", config.androidAppActivity());

        // Optional capabilities for better stability
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("disableWindowAnimation", true);
        capabilities.setCapability("skipDeviceInitialization", true);
        capabilities.setCapability("skipServerInstallation", true);
        capabilities.setCapability("ignoreHiddenApiPolicyError", true);

        log.debug("Android capabilities set successfully");
    }

    private void setIOSCapabilities(DesiredCapabilities capabilities) {
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("automationName", config.iosAutomationName());
        capabilities.setCapability("deviceName", config.iosDeviceName());
        capabilities.setCapability("platformVersion", config.iosPlatformVersion());
        capabilities.setCapability("bundleId", config.iosBundleId());

        // Optional capabilities for better stability
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("useNewWDA", false);
        capabilities.setCapability("wdaStartupRetries", 4);
        capabilities.setCapability("iosInstallPause", 8000);
        capabilities.setCapability("wdaStartupRetryInterval", 20000);

        log.debug("iOS capabilities set successfully");
    }

    public void updateCapability(String key, Object value) {
        try {
            DesiredCapabilities capabilities = getCapabilities();
            capabilities.setCapability(key, value);
            log.info("Updated capability: {} = {}", key, value);
        } catch (Exception e) {
            log.error("Failed to update capability: {}", e.getMessage());
            throw new RuntimeException("Capability update failed", e);
        }
    }

    public void setCustomCapability(String key, Object value, String platform) {
        if (platform.equalsIgnoreCase(config.platform())) {
            updateCapability(key, value);
        }
    }
}
