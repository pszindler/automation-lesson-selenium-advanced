package Pages.Checkout;

import Pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PaymentPage extends BasePage {
    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input#payment-option-1")
    private WebElement payByCheckOption;
    @FindBy(css = ".custom-checkbox .ps-shown-by-js")
    private WebElement termsOfServiceOption;
    @FindBy(css = ".btn.btn-primary.center-block")
    private WebElement placeOrderBtn;

    public void setPaymentOptions() {
        wait.until(ExpectedConditions.visibilityOf(placeOrderBtn));
        payByCheckOption.click();
        termsOfServiceOption.click();
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn));
        placeOrderBtn.click();
    }

}
