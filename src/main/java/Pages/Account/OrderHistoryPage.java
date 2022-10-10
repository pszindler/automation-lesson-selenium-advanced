package Pages.Account;

import Pages.BasePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OrderHistoryPage extends BasePage {
    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "tbody > tr")
    private List<WebElement> orders;

    public OrderHistoryRow getOrder(String orderReferenceNumber) {
        for (WebElement order : orders) {
            if (order.findElement(By.cssSelector("th")).getText().equals(orderReferenceNumber)) {
                return new OrderHistoryRow(driver, order);
            }
        }
        return null;
    }
}
