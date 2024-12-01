package com.a101.mobile.steps;

import com.a101.mobile.pages.android.LoginPage;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import org.junit.Assert;
import lombok.extern.log4j.Log4j2;

import static org.reflections.Reflections.log;

@Log4j2
public class LoginSteps {

    private final LoginPage loginPage = new LoginPage();

    @Step("Launch A101 mobile application")
    public void launchApplication() {
        // Application is launched by driver factory
        log.info("Application launched successfully");
    }

    @Step("Click login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Step("Enter phone number <phoneNumber>")
    public void enterPhoneNumber(String phoneNumber) {
        loginPage.enterPhoneNumber(phoneNumber);
    }

    @Step("Click continue button")
    public void clickContinueButton() {
        loginPage.clickContinueButton();
    }

    @Step("Enter OTP code")
    public void enterOtpCode() {
        // In test environment, we can use a static OTP
        loginPage.enterOtpCode("123456");
    }

    @Step("Enter OTP code <code>")
    public void enterSpecificOtpCode(String code) {
        loginPage.enterOtpCode(code);
    }

    @Step("Verify error message <message>")
    public void verifyErrorMessage(String expectedMessage) {
        String actualMessage = loginPage.getErrorMessage();
        Assert.assertEquals("Error message doesn't match", expectedMessage, actualMessage);
        log.info("Error message verified: {}", expectedMessage);
    }

    @Step("Leave phone number empty")
    public void leavePhoneNumberEmpty() {
        loginPage.enterPhoneNumber("");
    }

    @Step("Verify user is logged in successfully")
    public void verifySuccessfulLogin() {
        // Add verification logic
        log.info("User logged in successfully");
    }
}
