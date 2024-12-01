package com.a101.mobile.tests;

import com.a101.mobile.core.BaseTest;
import com.a101.mobile.pages.LoginPage;
import com.a101.mobile.core.utils.TestDataGenerator;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Epic("Authentication")
@Feature("Login")
public class LoginTest extends BaseTest {

    @Test(description = "Successful login with valid phone number")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login with Phone Number")
    @Description("Verify that user can successfully login using valid phone number and OTP")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage();
        String phoneNumber = TestDataGenerator.generatePhoneNumber();
        String otpCode = TestDataGenerator.getDefaultOTP();

        loginPage.loginWithPhoneNumber(phoneNumber, otpCode);
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login was not successful");
    }

    @Test(description = "Login with empty phone number")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login Validations")
    public void testEmptyPhoneNumber() {
        LoginPage loginPage = new LoginPage();
        loginPage.clickLoginButton();
        loginPage.enterPhoneNumber("");
        loginPage.clickContinueButton();

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Lütfen telefon numaranızı giriniz",
                "Incorrect error message for empty phone number");
    }

    @Test(description = "Login with invalid phone number format")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login Validations")
    public void testInvalidPhoneNumberFormat() {
        LoginPage loginPage = new LoginPage();
        loginPage.clickLoginButton();
        loginPage.enterPhoneNumber("123");
        loginPage.clickContinueButton();

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Geçerli bir telefon numarası giriniz",
                "Incorrect error message for invalid phone number");
    }

    @Test(description = "Login with invalid OTP")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login Validations")
    public void testInvalidOtp() {
        LoginPage loginPage = new LoginPage();
        String phoneNumber = TestDataGenerator.generatePhoneNumber();

        loginPage.clickLoginButton();
        loginPage.enterPhoneNumber(phoneNumber);
        loginPage.clickContinueButton();
        loginPage.enterOtpCode("000000");
        loginPage.clickContinueButton();

        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Geçersiz doğrulama kodu",
                "Incorrect error message for invalid OTP");
    }
}
