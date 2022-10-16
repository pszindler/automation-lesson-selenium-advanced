package Pages.Checkout;

import Pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static Helpers.UrlProvider.ORDER_HISTORY_URL;

public class ConfirmationPage extends BasePage {
    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".col-sm-4.col-xs-9.details > span")
    private WebElement productName;
    @FindBy(css = ".col-xs-4.text-sm-center.text-xs-left")
    private WebElement unitPrice;
    @FindBy(css = ".bold.col-xs-4.text-sm-center.text-xs-right")
    private WebElement totalPrice;
    @FindBy(css = "div#order-details > ul > li:nth-of-type(3) em")
    private WebElement shippingMethod;
    @FindBy(css = "div#order-details > ul > li:nth-of-type(2)")
    private WebElement paymentMethod;
    @FindBy(css = "tr:nth-of-type(2) > td:nth-of-type(2)")
    private WebElement shippingAndHandling;
    @FindBy(css = "section#content-hook_payment_return .col-md-12")
    private WebElement checkPaymentsDetails;
    @FindBy(css = "div#order-details > ul > li:nth-of-type(1)")
    private WebElement orderReferenceNumber;

    public String getProductName() {
        return productName.getText().split("-")[0].strip();
    }

    public double getProductPrice() {
        return getProductPrice(unitPrice);
    }

    public double getTotalPrice() {
        return getProductPrice(totalPrice);
    }

    public String getShippingMethod() {
        return shippingMethod.getText();
    }

    public String getPaymentMethod() {
        return getTextAfterColon(paymentMethod);
    }

    public String getOrderReferenceNumber() {
        return getTextAfterColon(orderReferenceNumber);
    }

    public ConfirmationPage goToOrderHistory() {
        driver.get(ORDER_HISTORY_URL);
        return this;
    }

    public String getShippingAndHandling() {
        return shippingAndHandling.getText();
    }
}
