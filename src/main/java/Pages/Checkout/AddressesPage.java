package Pages.Checkout;

import Pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressesPage extends BasePage {
    public AddressesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "p:nth-of-type(4) > a")
    private WebElement addInvoiceAddressFormBtn;
    @FindBy(css = "input[name='address1']")
    private WebElement addressInput;
    @FindBy(css = "input[name='postcode']")
    private WebElement zipcodeInput;
    @FindBy(css = "input[name='city']")
    private WebElement cityInput;
    @FindBy(css = "button[name='confirm-addresses']")
    private WebElement continueBtn;
    @FindBy(css = "article:nth-of-type(2)  .delete-address.text-muted")
    private WebElement deleteShippingAddress;

    public void setAddInvoiceAddressForm() {
        deleteShippingAddress.click();
        addInvoiceAddressFormBtn.click();
        addressInput.sendKeys("Orzechowska 21");
        zipcodeInput.sendKeys("44-119");
        cityInput.sendKeys("KTW");
        continueBtn.click();
    }
}
