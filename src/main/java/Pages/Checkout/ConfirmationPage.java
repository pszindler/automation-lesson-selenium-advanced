package Pages.Checkout;

import Pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    private final String orderHistoryLink = "http://146.59.32.4/index.php?controller=history";

    public String getProductName() {
        return productName.getText().split("-")[0].strip();
    }

    public double getProductPrice() {
        return Double.parseDouble(unitPrice.getText().replace("$", ""));
    }

    public double getTotalPrice() {
        return Double.parseDouble(totalPrice.getText().replace("$", ""));
    }

    public String getShippingMethod() {
        return shippingMethod.getText();
    }

    public String getPaymentMethod() {
        return paymentMethod.getText().split(":")[1].strip();
    }

    public String getOrderReferenceNumber() {
        return orderReferenceNumber.getText().split(":")[1].strip();
    }

    public ConfirmationPage goToOrderHistory() {
        driver.get(orderHistoryLink);
        return this;
    }

    public String getShippingAndHandling() {
        return shippingAndHandling.getText();
    }
}
