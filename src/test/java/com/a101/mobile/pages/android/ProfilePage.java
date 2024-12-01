package com.a101.mobile.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProfilePage extends BasePage {

    @AndroidFindBy(id = "com.a101.mobile:id/personalInfoTab")
    private WebElement personalInfoTab;

    @AndroidFindBy(id = "com.a101.mobile:id/addressesTab")
    private WebElement addressesTab;

    @AndroidFindBy(id = "com.a101.mobile:id/ordersTab")
    private WebElement ordersTab;

    @AndroidFindBy(id = "com.a101.mobile:id/favoritesTab")
    private WebElement favoritesTab;

    @AndroidFindBy(id = "com.a101.mobile:id/logoutButton")
    private WebElement logoutButton;

    @AndroidFindBy(id = "com.a101.mobile:id/deleteAccountButton")
    private WebElement deleteAccountButton;

    @AndroidFindBy(id = "com.a101.mobile:id/nameInput")
    private WebElement nameInput;

    @AndroidFindBy(id = "com.a101.mobile:id/surnameInput")
    private WebElement surnameInput;

    @AndroidFindBy(id = "com.a101.mobile:id/emailInput")
    private WebElement emailInput;

    @AndroidFindBy(id = "com.a101.mobile:id/updateButton")
    private WebElement updateButton;

    public void navigateToPersonalInfo() {
        try {
            click(personalInfoTab);
            log.info("Navigated to personal info tab");
        } catch (Exception e) {
            log.error("Failed to navigate to personal info: {}", e.getMessage());
            throw e;
        }
    }

    public void navigateToAddresses() {
        try {
            click(addressesTab);
            log.info("Navigated to addresses tab");
        } catch (Exception e) {
            log.error("Failed to navigate to addresses: {}", e.getMessage());
            throw e;
        }
    }

    public void navigateToOrders() {
        try {
            click(ordersTab);
            log.info("Navigated to orders tab");
        } catch (Exception e) {
            log.error("Failed to navigate to orders: {}", e.getMessage());
            throw e;
        }
    }

    public void navigateToFavorites() {
        try {
            click(favoritesTab);
            log.info("Navigated to favorites tab");
        } catch (Exception e) {
            log.error("Failed to navigate to favorites: {}", e.getMessage());
            throw e;
        }
    }

    public void updatePersonalInfo(String name, String surname, String email) {
        try {
            sendKeys(nameInput, name);
            sendKeys(surnameInput, surname);
            sendKeys(emailInput, email);
            click(updateButton);
            log.info("Updated personal info successfully");
        } catch (Exception e) {
            log.error("Failed to update personal info: {}", e.getMessage());
            throw e;
        }
    }

    public void logout() {
        try {
            click(logoutButton);
            log.info("Logged out successfully");
        } catch (Exception e) {
            log.error("Failed to logout: {}", e.getMessage());
            throw e;
        }
    }

    public void deleteAccount() {
        try {
            click(deleteAccountButton);
            // Handle confirmation dialog if needed
            log.info("Initiated account deletion");
        } catch (Exception e) {
            log.error("Failed to delete account: {}", e.getMessage());
            throw e;
        }
    }

    public void waitForProfilePageToLoad() {
        try {
            waitForElement(personalInfoTab);
            log.info("Profile page loaded successfully");
        } catch (Exception e) {
            log.error("Profile page did not load within expected time: {}", e.getMessage());
            throw e;
        }
    }
}
