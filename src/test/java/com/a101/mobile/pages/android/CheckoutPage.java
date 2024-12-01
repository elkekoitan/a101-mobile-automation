package com.a101.mobile.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class CheckoutPage extends BasePage {

    @AndroidFindBy(id = "com.a101.mobile:id/deliveryAddressSection")
    private WebElement deliveryAddressSection;

    @AndroidFindBy(id = "com.a101.mobile:id/paymentMethodSection")
    private WebElement paymentMethodSection;

    @AndroidFindBy(id = "com.a101.mobile:id/cardNumberInput")
    private WebElement cardNumberInput;

    @AndroidFindBy(id = "com.a101.mobile:id/cardExpiryInput")
    private WebElement cardExpiryInput;

    @AndroidFindBy(id = "com.a101.mobile:id/cardCVVInput")
    private WebElement cardCVVInput;

    @AndroidFindBy(id = "com.a101.mobile:id/hadiKrediCheckbox")
    private WebElement hadiKrediCheckbox;

    @AndroidFindBy(id = "com.a101.mobile:id/placeOrderButton")
    private WebElement placeOrderButton;

    @AndroidFindBy(id = "com.a101.mobile:id/termsCheckbox")
    private WebElement termsCheckbox;

    public void enterCardDetails(String cardNumber, String expiry, String cvv) {
        try {
            sendKeys(cardNumberInput, cardNumber);
            sendKeys(cardExpiryInput, expiry);
            sendKeys(cardCVVInput, cvv);
            log.info("Entered card details successfully");
        } catch (Exception e) {
            log.error("Failed to enter card details: {}", e.getMessage());
            throw e;
        }
    }

    public void selectHadiKredi() {
        try {
            click(hadiKrediCheckbox);
            log.info("Selected Hadi Kredi payment method");
        } catch (Exception e) {
            log.error("Failed to select Hadi Kredi: {}", e.getMessage());
            throw e;
        }
    }

    public void acceptTerms() {
        try {
            click(termsCheckbox);
            log.info("Accepted terms and conditions");
        } catch (Exception e) {
            log.error("Failed to accept terms: {}", e.getMessage());
            throw e;
        }
    }

    public void placeOrder() {
        try {
            click(placeOrderButton);
            log.info("Clicked place order button");
        } catch (Exception e) {
            log.error("Failed to place order: {}", e.getMessage());
            throw e;
        }
    }

    public boolean isDeliveryAddressDisplayed() {
        return isElementDisplayed(deliveryAddressSection);
    }

    public boolean isPaymentMethodDisplayed() {
        return isElementDisplayed(paymentMethodSection);
    }

    public void waitForCheckoutPageToLoad() {
        try {
            waitForElement(deliveryAddressSection);
            waitForElement(paymentMethodSection);
            log.info("Checkout page loaded successfully");
        } catch (Exception e) {
            log.error("Checkout page did not load within expected time: {}", e.getMessage());
            throw e;
        }
    }
}
