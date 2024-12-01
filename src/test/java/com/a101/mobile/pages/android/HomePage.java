package com.a101.mobile.pages.android;

import com.a101.mobile.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class HomePage extends BasePage {

    @AndroidFindBy(id = "com.a101.mobile:id/profileButton")
    private WebElement profileButton;

    @AndroidFindBy(id = "com.a101.mobile:id/cartButton")
    private WebElement cartButton;

    @AndroidFindBy(id = "com.a101.mobile:id/searchBox")
    private WebElement searchBox;

    @AndroidFindBy(id = "com.a101.mobile:id/categoryList")
    private WebElement categoryList;

    @AndroidFindBy(id = "com.a101.mobile:id/campaignBanner")
    private List<WebElement> campaignBanners;

    @AndroidFindBy(id = "com.a101.mobile:id/deliveryAddressText")
    private WebElement deliveryAddressText;

    public void clickProfileButton() {
        click(profileButton);
        log.info("Clicked on profile button");
    }

    public void clickCartButton() {
        click(cartButton);
        log.info("Clicked on cart button");
    }

    public void searchProduct(String productName) {
        sendKeys(searchBox, productName);
        hideKeyboard();
        log.info("Searched for product: {}", productName);
    }

    public void selectCategory(String categoryName) {
        // TODO: Implement category selection logic
        log.info("Selected category: {}", categoryName);
    }

    public String getDeliveryAddress() {
        String address = getText(deliveryAddressText);
        log.info("Current delivery address: {}", address);
        return address;
    }

    public boolean isUserLoggedIn() {
        return isElementDisplayed(profileButton);
    }

    public boolean areCampaignBannersDisplayed() {
        return !campaignBanners.isEmpty() && campaignBanners.get(0).isDisplayed();
    }

    public void scrollToCampaigns() {
        // TODO: Implement scroll logic
        log.info("Scrolled to campaign section");
    }

    public void refreshPage() {
        // TODO: Implement refresh logic
        log.info("Page refreshed");
    }
}
