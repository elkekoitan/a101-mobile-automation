package com.a101.mobile.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class OrderPage extends BasePage {

    @AndroidFindBy(id = "com.a101.mobile:id/orderList")
    private WebElement orderList;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id, 'orderNumber')]")
    private List<WebElement> orderNumbers;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@resource-id, 'orderStatus')]")
    private List<WebElement> orderStatuses;

    @AndroidFindBy(id = "com.a101.mobile:id/orderDetailButton")
    private List<WebElement> orderDetailButtons;

    @AndroidFindBy(id = "com.a101.mobile:id/orderCancelButton")
    private WebElement orderCancelButton;

    @AndroidFindBy(id = "com.a101.mobile:id/returnOrderButton")
    private WebElement returnOrderButton;

    @AndroidFindBy(id = "com.a101.mobile:id/returnReasonDropdown")
    private WebElement returnReasonDropdown;

    @AndroidFindBy(id = "com.a101.mobile:id/confirmReturnButton")
    private WebElement confirmReturnButton;

    @AndroidFindBy(id = "com.a101.mobile:id/orderTrackingButton")
    private WebElement orderTrackingButton;

    @AndroidFindBy(id = "com.a101.mobile:id/orderInvoiceButton")
    private WebElement orderInvoiceButton;

    @AndroidFindBy(id = "com.a101.mobile:id/addReviewButton")
    private WebElement addReviewButton;

    @AndroidFindBy(id = "com.a101.mobile:id/ratingBar")
    private WebElement ratingBar;

    @AndroidFindBy(id = "com.a101.mobile:id/reviewComment")
    private WebElement reviewComment;

    @AndroidFindBy(id = "com.a101.mobile:id/submitReviewButton")
    private WebElement submitReviewButton;

    public void openOrderDetail(int index) {
        try {
            if (index < orderDetailButtons.size()) {
                click(orderDetailButtons.get(index));
                log.info("Opened order detail at index: {}", index);
            } else {
                throw new IndexOutOfBoundsException("Order index out of range");
            }
        } catch (Exception e) {
            log.error("Failed to open order detail: {}", e.getMessage());
            throw e;
        }
    }

    public String getOrderNumber(int index) {
        try {
            if (index < orderNumbers.size()) {
                String orderNumber = getText(orderNumbers.get(index));
                log.debug("Got order number at index {}: {}", index, orderNumber);
                return orderNumber;
            } else {
                throw new IndexOutOfBoundsException("Order index out of range");
            }
        } catch (Exception e) {
            log.error("Failed to get order number: {}", e.getMessage());
            throw e;
        }
    }

    public String getOrderStatus(int index) {
        try {
            if (index < orderStatuses.size()) {
                String status = getText(orderStatuses.get(index));
                log.debug("Got order status at index {}: {}", index, status);
                return status;
            } else {
                throw new IndexOutOfBoundsException("Order index out of range");
            }
        } catch (Exception e) {
            log.error("Failed to get order status: {}", e.getMessage());
            throw e;
        }
    }

    public void cancelOrder() {
        try {
            if (isElementDisplayed(orderCancelButton)) {
                click(orderCancelButton);
                // Handle confirmation dialog if needed
                log.info("Cancelled order successfully");
            } else {
                throw new IllegalStateException("Cancel button is not available");
            }
        } catch (Exception e) {
            log.error("Failed to cancel order: {}", e.getMessage());
            throw e;
        }
    }

    public void initiateReturn(String reason) {
        try {
            click(returnOrderButton);
            click(returnReasonDropdown);
            // Select reason from dropdown
            selectFromDropdown(reason);
            click(confirmReturnButton);
            log.info("Initiated return with reason: {}", reason);
        } catch (Exception e) {
            log.error("Failed to initiate return: {}", e.getMessage());
            throw e;
        }
    }

    public void trackOrder() {
        try {
            click(orderTrackingButton);
            log.info("Opened order tracking");
        } catch (Exception e) {
            log.error("Failed to track order: {}", e.getMessage());
            throw e;
        }
    }

    public void viewInvoice() {
        try {
            click(orderInvoiceButton);
            log.info("Opened order invoice");
        } catch (Exception e) {
            log.error("Failed to view invoice: {}", e.getMessage());
            throw e;
        }
    }

    public void addReview(int rating, String comment) {
        try {
            click(addReviewButton);
            // Set rating (implementation depends on your rating bar)
            setRating(rating);
            sendKeys(reviewComment, comment);
            click(submitReviewButton);
            log.info("Added review with rating {} and comment", rating);
        } catch (Exception e) {
            log.error("Failed to add review: {}", e.getMessage());
            throw e;
        }
    }

    private void setRating(int rating) {
        // Implementation for setting rating
        // This might need to be customized based on your app's implementation
    }

    private void selectFromDropdown(String value) {
        // Implementation for selecting value from dropdown
        // This might need to be customized based on your app's implementation
    }

    public void waitForOrderPageToLoad() {
        try {
            waitForElement(orderList);
            log.info("Order page loaded successfully");
        } catch (Exception e) {
            log.error("Order page did not load within expected time: {}", e.getMessage());
            throw e;
        }
    }

    public boolean isReturnAvailable() {
        return isElementDisplayed(returnOrderButton);
    }

    public boolean isCancellationAvailable() {
        return isElementDisplayed(orderCancelButton);
    }

    public boolean isReviewAvailable() {
        return isElementDisplayed(addReviewButton);
    }
}
