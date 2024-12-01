package com.a101.mobile.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CartPage extends BasePage {

    @AndroidFindBy(id = "com.a101.mobile:id/cartItemList")
    private WebElement cartItemList;

    @AndroidFindBy(id = "com.a101.mobile:id/continueButton")
    private WebElement continueButton;

    @AndroidFindBy(id = "com.a101.mobile:id/couponCodeInput")
    private WebElement couponCodeInput;

    @AndroidFindBy(id = "com.a101.mobile:id/applyCouponButton")
    private WebElement applyCouponButton;

    @AndroidFindBy(id = "com.a101.mobile:id/deleteItemButton")
    private List<WebElement> deleteItemButtons;

    @AndroidFindBy(id = "com.a101.mobile:id/totalPrice")
    private WebElement totalPrice;

    @AndroidFindBy(id = "com.a101.mobile:id/selectAllCheckbox")
    private WebElement selectAllCheckbox;

    public void clickContinueButton() {
        try {
            click(continueButton);
            log.info("Clicked continue button in cart");
        } catch (Exception e) {
            log.error("Failed to click continue button: {}", e.getMessage());
            throw e;
        }
    }

    public void applyCouponCode(String code) {
        try {
            sendKeys(couponCodeInput, code);
            click(applyCouponButton);
            log.info("Applied coupon code: {}", code);
        } catch (Exception e) {
            log.error("Failed to apply coupon code: {}", e.getMessage());
            throw e;
        }
    }

    public void removeAllItems() {
        try {
            if (!deleteItemButtons.isEmpty()) {
                click(selectAllCheckbox);
                click(deleteItemButtons.get(0));
                log.info("Removed all items from cart");
            }
        } catch (Exception e) {
            log.error("Failed to remove items from cart: {}", e.getMessage());
            throw e;
        }
    }

    public String getTotalPrice() {
        try {
            String price = getText(totalPrice);
            log.debug("Cart total price: {}", price);
            return price;
        } catch (Exception e) {
            log.error("Failed to get total price: {}", e.getMessage());
            throw e;
        }
    }

    public boolean isCartEmpty() {
        try {
            return !isElementDisplayed(cartItemList);
        } catch (Exception e) {
            log.debug("Cart appears to be empty");
            return true;
        }
    }

    public void waitForCartToLoad() {
        try {
            waitForElement(cartItemList);
            log.debug("Cart loaded successfully");
        } catch (Exception e) {
            log.error("Cart did not load within expected time: {}", e.getMessage());
            throw e;
        }
    }
}
