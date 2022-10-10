package Pages.Account;

import Pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderHistoryRow extends BasePage {
    public OrderHistoryRow(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    @FindBy(css = "th")
    private WebElement orderReference;
    @FindBy(css = "td:nth-child(2)")
    private WebElement orderDate;
    @FindBy(css = "td:nth-child(3)")
    private WebElement totalPrice;
    @FindBy(css = "td:nth-child(4)")
    private WebElement paymentType;
    @FindBy(css = "td:nth-child(5) .label")
    private WebElement status;
    @FindBy(css = "td:nth-child(6)")
    private WebElement invoiceStatus;

    public double getTotalPrice() {
        return Double.parseDouble(totalPrice.getText().replace("$", ""));
    }

    public String getPayment() {
        return paymentType.getText();
    }

    public String getOrderDate() {
        return orderDate.getText();
    }

    public String getStatus() {
        return status.getText();
    }

    public String getInvoice() {
        return invoiceStatus.getText();
    }


}
