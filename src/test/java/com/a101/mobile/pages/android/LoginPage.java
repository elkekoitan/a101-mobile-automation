package com.a101.mobile.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginPage extends BasePage {

    @AndroidFindBy(id = "com.a101.mobile:id/btnLogin")
    @iOSXCUITFindBy(accessibility = "loginButton")
    private WebElement loginButton;

    @AndroidFindBy(id = "com.a101.mobile:id/etPhoneNumber")
    @iOSXCUITFindBy(accessibility = "phoneNumberInput")
    private WebElement phoneNumberInput;

    @AndroidFindBy(id = "com.a101.mobile:id/btnContinue")
    @iOSXCUITFindBy(accessibility = "continueButton")
    private WebElement continueButton;

    @AndroidFindBy(id = "com.a101.mobile:id/etOtpCode")
    @iOSXCUITFindBy(accessibility = "otpCodeInput")
    private WebElement otpCodeInput;

    @AndroidFindBy(id = "com.a101.mobile:id/tvErrorMessage")
    @iOSXCUITFindBy(accessibility = "errorMessage")
    private WebElement errorMessage;

    public void clickLoginButton() {
        click(loginButton);
        log.info("Clicked on login button");
    }

    public void enterPhoneNumber(String phoneNumber) {
        sendKeys(phoneNumberInput, phoneNumber);
        hideKeyboard();
        log.info("Entered phone number: {}", phoneNumber);
    }

    public void clickContinueButton() {
        click(continueButton);
        log.info("Clicked on continue button");
    }

    public void enterOtpCode(String otpCode) {
        sendKeys(otpCodeInput, otpCode);
        hideKeyboard();
        log.info("Entered OTP code: {}", otpCode);
    }

    public String getErrorMessage() {
        String message = getText(errorMessage);
        log.info("Error message displayed: {}", message);
        return message;
    }

    public boolean isLoginButtonDisplayed() {
        return isElementDisplayed(loginButton);
    }

    public boolean isPhoneNumberInputDisplayed() {
        return isElementDisplayed(phoneNumberInput);
    }

    public void loginWithPhoneNumber(String phoneNumber, String otpCode) {
        clickLoginButton();
        enterPhoneNumber(phoneNumber);
        clickContinueButton();
        enterOtpCode(otpCode);
        clickContinueButton();
        log.info("Completed login flow with phone number: {}", phoneNumber);
    }
}
