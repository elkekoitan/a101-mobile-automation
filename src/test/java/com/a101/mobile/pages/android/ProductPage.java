package com.a101.mobile.pages.android;

import com.a101.mobile.pages.BasePage;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProductPage extends BasePage {

    @AndroidFindBy(id = "com.a101.mobile:id/productName")
    private WebElement productName;

    @AndroidFindBy(id = "com.a101.mobile:id/productPrice")
    private WebElement productPrice;

    @AndroidFindBy(id = "com.a101.mobile:id/addToCartButton")
    private WebElement addToCartButton;

    @AndroidFindBy(id = "com.a101.mobile:id/favoriteButton")
    private WebElement favoriteButton;

    @AndroidFindBy(id = "com.a101.mobile:id/productImage")
    private WebElement productImage;

    @AndroidFindBy(id = "com.a101.mobile:id/productDescription")
    private WebElement productDescription;

    @AndroidFindBy(id = "com.a101.mobile:id/productQuantity")
    private WebElement quantitySpinner;

    @AndroidFindBy(id = "com.a101.mobile:id/similarProducts")
    private List<WebElement> similarProducts;

    public void addToCart() {
        click(addToCartButton);
        log.info("Added product to cart");
    }

    public void addToFavorites() {
        click(favoriteButton);
        log.info("Added product to favorites");
    }

    public String getProductName() {
        String name = getText(productName);
        log.info("Product name: {}", name);
        return name;
    }

    public String getProductPrice() {
        String price = getText(productPrice);
        log.info("Product price: {}", price);
        return price;
    }

    public void selectQuantity(int quantity) {
        // TODO: Implement quantity selection logic
        log.info("Selected quantity: {}", quantity);
    }

    public boolean isProductImageDisplayed() {
        return isElementDisplayed(productImage);
    }

    public String getProductDescription() {
        return getText(productDescription);
    }

    public void scrollToSimilarProducts() {
        // TODO: Implement scroll logic
        log.info("Scrolled to similar products section");
    }

    public boolean areSimilarProductsDisplayed() {
        return !similarProducts.isEmpty() && similarProducts.get(0).isDisplayed();
    }

    public void selectSimilarProduct(int index) {
        if (index < similarProducts.size()) {
            click(similarProducts.get(index));
            log.info("Selected similar product at index: {}", index);
        } else {
            log.error("Invalid similar product index: {}", index);
        }
    }
}
