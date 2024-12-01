package com.a101.mobile.core.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.util.Collections;

@Log4j2
public class GestureUtils {
    private final AppiumDriver driver;
    private static final int SCROLL_DURATION = 1000;
    private static final int SWIPE_DURATION = 800;
    private static final double SCROLL_RATIO = 0.7;
    private static final int MAX_SWIPE_ATTEMPTS = 3;

    public GestureUtils(AppiumDriver driver) {
        this.driver = driver;
    }

    public void tap(WebElement element) {
        try {
            Point location = element.getLocation();
            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence tap = new Sequence(finger, 1)
                    .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), location.getX(), location.getY()))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(new Pause(finger, Duration.ofMillis(200)))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(tap));
            log.debug("Tapped on element at location: ({}, {})", location.getX(), location.getY());
        } catch (Exception e) {
            log.error("Failed to perform tap gesture: {}", e.getMessage());
            throw new RuntimeException("Tap gesture failed", e);
        }
    }

    public void swipeUp() {
        swipe(SwipeDirection.UP);
    }

    public void swipeDown() {
        swipe(SwipeDirection.DOWN);
    }

    public void swipeLeft() {
        swipe(SwipeDirection.LEFT);
    }

    public void swipeRight() {
        swipe(SwipeDirection.RIGHT);
    }

    private void swipe(SwipeDirection direction) {
        try {
            Dimension size = driver.manage().window().getSize();
            Point start = getSwipeStartPoint(size, direction);
            Point end = getSwipeEndPoint(size, direction);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1)
                    .addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), start.getX(), start.getY()))
                    .addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                    .addAction(finger.createPointerMove(Duration.ofMillis(SWIPE_DURATION), PointerInput.Origin.viewport(), end.getX(), end.getY()))
                    .addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            driver.perform(Collections.singletonList(swipe));
            log.debug("Performed {} swipe gesture", direction);
        } catch (Exception e) {
            log.error("Failed to perform {} swipe gesture: {}", direction, e.getMessage());
            throw new RuntimeException("Swipe gesture failed", e);
        }
    }

    public void scrollToText(String text) {
        int attempts = 0;
        boolean elementFound = false;

        while (!elementFound && attempts < MAX_SWIPE_ATTEMPTS) {
            try {
                driver.findElement(org.openqa.selenium.By.xpath("//*[@text='" + text + "']"));
                elementFound = true;
            } catch (Exception e) {
                swipeUp();
                attempts++;
            }
        }

        if (!elementFound) {
            log.error("Element with text '{}' not found after {} attempts", text, MAX_SWIPE_ATTEMPTS);
            throw new RuntimeException("Element not found: " + text);
        }
    }

    private Point getSwipeStartPoint(Dimension size, SwipeDirection direction) {
        switch (direction) {
            case UP:
                return new Point(size.width / 2, (int) (size.height * SCROLL_RATIO));
            case DOWN:
                return new Point(size.width / 2, (int) (size.height * (1 - SCROLL_RATIO)));
            case LEFT:
                return new Point((int) (size.width * SCROLL_RATIO), size.height / 2);
            case RIGHT:
                return new Point((int) (size.width * (1 - SCROLL_RATIO)), size.height / 2);
            default:
                throw new IllegalArgumentException("Invalid swipe direction: " + direction);
        }
    }

    private Point getSwipeEndPoint(Dimension size, SwipeDirection direction) {
        switch (direction) {
            case UP:
                return new Point(size.width / 2, (int) (size.height * (1 - SCROLL_RATIO)));
            case DOWN:
                return new Point(size.width / 2, (int) (size.height * SCROLL_RATIO));
            case LEFT:
                return new Point((int) (size.width * (1 - SCROLL_RATIO)), size.height / 2);
            case RIGHT:
                return new Point((int) (size.width * SCROLL_RATIO), size.height / 2);
            default:
                throw new IllegalArgumentException("Invalid swipe direction: " + direction);
        }
    }

    private enum SwipeDirection {
        UP, DOWN, LEFT, RIGHT
    }
}
