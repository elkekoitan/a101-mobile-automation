package com.a101.mobile.pages;

import com.a101.mobile.core.driver.DriverManager;
import com.a101.mobile.core.utils.WaitUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.time.Duration;
import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class BasePage {
    protected AppiumDriver driver;
    protected WaitUtils wait;
    private static final int WAIT_TIMEOUT = 15;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait = new WaitUtils(driver, WAIT_TIMEOUT);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(WAIT_TIMEOUT)), this);
    }

    protected void click(WebElement element) {
        try {
            wait.waitForElementToBeClickable(element).click();
            log.debug("Clicked on element: {}", element);
        } catch (Exception e) {
            log.error("Failed to click element: {}", e.getMessage());
            throw e;
        }
    }

    protected void sendKeys(WebElement element, String text) {
        try {
            wait.waitForElementToBeVisible(element);
            element.clear();
            element.sendKeys(text);
            log.debug("Entered text '{}' into element", text);
        } catch (Exception e) {
            log.error("Failed to enter text: {}", e.getMessage());
            throw e;
        }
    }

    protected String getText(WebElement element) {
        try {
            wait.waitForElementToBeVisible(element);
            String text = element.getText();
            log.debug("Got text '{}' from element", text);
            return text;
        } catch (Exception e) {
            log.error("Failed to get text: {}", e.getMessage());
            throw e;
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            return wait.waitForElementToBeVisible(element).isDisplayed();
        } catch (Exception e) {
            log.debug("Element is not displayed");
            return false;
        }
    }

    protected boolean isElementEnabled(WebElement element) {
        try {
            return wait.waitForElementToBeVisible(element).isEnabled();
        } catch (Exception e) {
            log.debug("Element is not enabled");
            return false;
        }
    }

    protected void hideKeyboard() {
        try {
            if (driver.isKeyboardShown()) {
                driver.hideKeyboard();
                log.debug("Keyboard hidden successfully");
            }
        } catch (Exception e) {
            log.error("Failed to hide keyboard: {}", e.getMessage());
        }
    }

    protected void waitForLoading() {
        // Implement specific loading indicator wait logic
        try {
            Thread.sleep(1000); // Basic implementation
            log.debug("Waited for loading");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Loading wait interrupted");
        }
    }
}
