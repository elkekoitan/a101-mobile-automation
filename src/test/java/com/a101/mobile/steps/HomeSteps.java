package com.a101.mobile.steps;

import com.a101.mobile.pages.android.HomePage;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class HomeSteps {

    private final HomePage homePage = new HomePage();

    @Step("Verify user is on homepage")
    public void verifyHomepage() {
        Assert.assertTrue("User is not on homepage", homePage.isUserLoggedIn());
        log.info("Verified user is on homepage");
    }

    @Step("Search for product <productName>")
    public void searchProduct(String productName) {
        homePage.searchProduct(productName);
    }

    @Step("Click on cart button")
    public void clickCartButton() {
        homePage.clickCartButton();
    }

    @Step("Click on profile button")
    public void clickProfileButton() {
        homePage.clickProfileButton();
    }

    @Step("Select category <categoryName>")
    public void selectCategory(String categoryName) {
        homePage.selectCategory(categoryName);
    }

    @Step("Verify delivery address is displayed")
    public void verifyDeliveryAddress() {
        Assert.assertFalse("Delivery address is empty",
                homePage.getDeliveryAddress().isEmpty());
        log.info("Verified delivery address is displayed");
    }

    @Step("Verify campaign banners are displayed")
    public void verifyCampaignBanners() {
        Assert.assertTrue("Campaign banners are not displayed",
                homePage.areCampaignBannersDisplayed());
        log.info("Verified campaign banners are displayed");
    }
}
