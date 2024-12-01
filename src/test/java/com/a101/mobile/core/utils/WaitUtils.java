package com.a101.mobile.core.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class WaitUtils {
    private final WebDriverWait wait;
    private final AppiumDriver driver;
    private final int timeoutInSeconds;
    private final int pollingInSeconds;

    public WaitUtils(AppiumDriver driver, int timeoutInSeconds) {
        this(driver, timeoutInSeconds, 1);
    }

    public WaitUtils(AppiumDriver driver, int timeoutInSeconds, int pollingInSeconds) {
        this.driver = driver;
        this.timeoutInSeconds = timeoutInSeconds;
        this.pollingInSeconds = pollingInSeconds;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds), Duration.ofSeconds(pollingInSeconds));
        wait.ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
    }

    public WebElement waitForElement(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            log.error("Element not found after {} seconds: {}", timeoutInSeconds, locator);
            throw new TimeoutException("Element not found: " + locator, e);
        }
    }

    public WebElement waitForElementToBeVisible(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            log.error("Element not visible after {} seconds", timeoutInSeconds);
            throw new TimeoutException("Element not visible", e);
        }
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            log.error("Element not clickable after {} seconds", timeoutInSeconds);
            throw new TimeoutException("Element not clickable", e);
        }
    }

    public boolean waitForElementToDisappear(WebElement element) {
        try {
            return wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException e) {
            log.error("Element still visible after {} seconds", timeoutInSeconds);
            return false;
        }
    }

    public void waitForPageToLoad() {
        try {
            wait.until(driver -> ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState")
                    .equals("complete"));
            log.debug("Page loaded successfully");
        } catch (TimeoutException e) {
            log.error("Page load timeout after {} seconds", timeoutInSeconds);
            throw new TimeoutException("Page load timeout", e);
        }
    }

    public void waitForTextToBePresentInElement(WebElement element, String text) {
        try {
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            log.debug("Text '{}' present in element", text);
        } catch (TimeoutException e) {
            log.error("Text '{}' not present in element after {} seconds", text, timeoutInSeconds);
            throw new TimeoutException("Text not present in element: " + text, e);
        }
    }

    public void waitForAttributeToBe(WebElement element, String attribute, String value) {
        try {
            wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
            log.debug("Attribute '{}' has value '{}'", attribute, value);
        } catch (TimeoutException e) {
            log.error("Attribute '{}' did not have value '{}' after {} seconds", attribute, value, timeoutInSeconds);
            throw new TimeoutException("Attribute value not matched", e);
        }
    }

    public <T> T waitFor(Function<AppiumDriver, T> condition) {
        try {
            return wait.until(condition);
        } catch (TimeoutException e) {
            log.error("Custom condition not met after {} seconds", timeoutInSeconds);
            throw new TimeoutException("Custom condition timeout", e);
        }
    }

    public void waitForAlert() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            log.debug("Alert is present");
        } catch (TimeoutException e) {
            log.error("Alert not present after {} seconds", timeoutInSeconds);
            throw new TimeoutException("Alert not present", e);
        }
    }

    public List<WebElement> waitForElements(By locator) {
        try {
            return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
        } catch (TimeoutException e) {
            log.error("Elements not found after {} seconds: {}", timeoutInSeconds, locator);
            throw new TimeoutException("Elements not found: " + locator, e);
        }
    }

    public void waitForElementToContainText(WebElement element, String text) {
        try {
            wait.until(driver -> element.getText().contains(text));
            log.debug("Element contains text: {}", text);
        } catch (TimeoutException e) {
            log.error("Element did not contain text '{}' after {} seconds", text, timeoutInSeconds);
            throw new TimeoutException("Text not found in element: " + text, e);
        }
    }
}
