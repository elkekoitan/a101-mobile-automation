package com.a101.mobile.pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class AddressPage extends BasePage {

    @AndroidFindBy(id = "com.a101.mobile:id/addNewAddressButton")
    private WebElement addNewAddressButton;

    @AndroidFindBy(id = "com.a101.mobile:id/addressList")
    private WebElement addressList;

    @AndroidFindBy(id = "com.a101.mobile:id/addressTypeHome")
    private WebElement homeAddressType;

    @AndroidFindBy(id = "com.a101.mobile:id/addressTypeWork")
    private WebElement workAddressType;

    @AndroidFindBy(id = "com.a101.mobile:id/addressTypeOther")
    private WebElement otherAddressType;

    @AndroidFindBy(id = "com.a101.mobile:id/addressTitle")
    private WebElement addressTitleInput;

    @AndroidFindBy(id = "com.a101.mobile:id/addressName")
    private WebElement nameInput;

    @AndroidFindBy(id = "com.a101.mobile:id/addressSurname")
    private WebElement surnameInput;

    @AndroidFindBy(id = "com.a101.mobile:id/phoneNumber")
    private WebElement phoneNumberInput;

    @AndroidFindBy(id = "com.a101.mobile:id/citySelection")
    private WebElement citySelection;

    @AndroidFindBy(id = "com.a101.mobile:id/districtSelection")
    private WebElement districtSelection;

    @AndroidFindBy(id = "com.a101.mobile:id/neighborhoodSelection")
    private WebElement neighborhoodSelection;

    @AndroidFindBy(id = "com.a101.mobile:id/addressDetail")
    private WebElement addressDetailInput;

    @AndroidFindBy(id = "com.a101.mobile:id/saveAddressButton")
    private WebElement saveAddressButton;

    @AndroidFindBy(id = "com.a101.mobile:id/corporateInvoiceCheckbox")
    private WebElement corporateInvoiceCheckbox;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id, 'editButton')]")
    private List<WebElement> editButtons;

    @AndroidFindBy(xpath = "//android.widget.Button[contains(@resource-id, 'deleteButton')]")
    private List<WebElement> deleteButtons;

    public void clickAddNewAddress() {
        try {
            click(addNewAddressButton);
            log.info("Clicked add new address button");
        } catch (Exception e) {
            log.error("Failed to click add new address button: {}", e.getMessage());
            throw e;
        }
    }

    public void fillAddressForm(String title, String name, String surname, String phone, String city,
                                String district, String neighborhood, String detail) {
        try {
            sendKeys(addressTitleInput, title);
            sendKeys(nameInput, name);
            sendKeys(surnameInput, surname);
            sendKeys(phoneNumberInput, phone);

            click(citySelection);
            selectFromDropdown(city);

            click(districtSelection);
            selectFromDropdown(district);

            click(neighborhoodSelection);
            selectFromDropdown(neighborhood);

            sendKeys(addressDetailInput, detail);

            log.info("Filled address form for: {}", title);
        } catch (Exception e) {
            log.error("Failed to fill address form: {}", e.getMessage());
            throw e;
        }
    }

    public void selectAddressType(AddressType type) {
        try {
            switch (type) {
                case HOME:
                    click(homeAddressType);
                    break;
                case WORK:
                    click(workAddressType);
                    break;
                case OTHER:
                    click(otherAddressType);
                    break;
            }
            log.info("Selected address type: {}", type);
        } catch (Exception e) {
            log.error("Failed to select address type: {}", e.getMessage());
            throw e;
        }
    }

    public void toggleCorporateInvoice(boolean enable) {
        try {
            if (enable != corporateInvoiceCheckbox.isSelected()) {
                click(corporateInvoiceCheckbox);
            }
            log.info("Set corporate invoice to: {}", enable);
        } catch (Exception e) {
            log.error("Failed to toggle corporate invoice: {}", e.getMessage());
            throw e;
        }
    }

    public void saveAddress() {
        try {
            click(saveAddressButton);
            log.info("Saved address successfully");
        } catch (Exception e) {
            log.error("Failed to save address: {}", e.getMessage());
            throw e;
        }
    }

    public void editAddress(int index) {
        try {
            if (index < editButtons.size()) {
                click(editButtons.get(index));
                log.info("Clicked edit button for address at index: {}", index);
            } else {
                throw new IndexOutOfBoundsException("Address index out of range");
            }
        } catch (Exception e) {
            log.error("Failed to edit address: {}", e.getMessage());
            throw e;
        }
    }

    public void deleteAddress(int index) {
        try {
            if (index < deleteButtons.size()) {
                click(deleteButtons.get(index));
                log.info("Clicked delete button for address at index: {}", index);
                // Handle confirmation dialog if needed
            } else {
                throw new IndexOutOfBoundsException("Address index out of range");
            }
        } catch (Exception e) {
            log.error("Failed to delete address: {}", e.getMessage());
            throw e;
        }
    }

    public enum AddressType {
        HOME,
        WORK,
        OTHER
    }

    private void selectFromDropdown(String value) {
        // Implementation for selecting value from dropdown
        // This might need to be customized based on your app's implementation
    }

    public void waitForAddressPageToLoad() {
        try {
            waitForElement(addressList);
            log.info("Address page loaded successfully");
        } catch (Exception e) {
            log.error("Address page did not load within expected time: {}", e.getMessage());
            throw e;
        }
    }
}
