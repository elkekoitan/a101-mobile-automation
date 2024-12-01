package com.a101.mobile.steps;

import com.a101.mobile.pages.android.ProductPage;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import lombok.extern.log4j.Log4j2;

import static org.reflections.Reflections.log;

@Log4j2
public class ProductSteps {

    private final ProductPage productPage = new ProductPage();

    @Step("Add product to cart")
    public void addToCart() {
        productPage.addToCart();
    }

    @Step("Add product to favorites")
    public void addToFavorites() {
        productPage.addToFavorites();
    }

    @Step("Verify product name is <name>")
    public void verifyProductName(String expectedName) {
        String actualName = productPage.getProductName();
        Assert.assertEquals("Product name doesn't match", expectedName, actualName);
        log.info("Verified product name: {}", expectedName);
    }

    @Step("Verify product price is <price>")
    public void verifyProductPrice(String expectedPrice) {
        String actualPrice = productPage.getProductPrice();
        Assert.assertEquals("Product price doesn't match", expectedPrice, actualPrice);
        log.info("Verified product price: {}", expectedPrice);
    }

    @Step("Select quantity <quantity>")
    public void selectQuantity(int quantity) {
        productPage.selectQuantity(quantity);
    }

    @Step("Verify product image is displayed")
    public void verifyProductImage() {
        Assert.assertTrue("Product image is not displayed",
                productPage.isProductImageDisplayed());
        log.info("Verified product image is displayed");
    }

    @Step("Verify similar products are displayed")
    public void verifySimilarProducts() {
        Assert.assertTrue("Similar products are not displayed",
                productPage.areSimilarProductsDisplayed());
        log.info("Verified similar products are displayed");
    }

    @Step("Select similar product at index <index>")
    public void selectSimilarProduct(int index) {
        productPage.selectSimilarProduct(index);
    }

    @Step("Scroll to similar products section")
    public void scrollToSimilarProducts() {
        productPage.scrollToSimilarProducts();
    }
}
