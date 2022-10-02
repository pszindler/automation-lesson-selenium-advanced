package Pages.Product;

import Pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    @FindBy(css = ".price")
    private WebElement productPrice;
    @FindBy(css = ".product-title")
    private WebElement productTitle;

    public double getProductPrice() {
        return Double.parseDouble(productPrice.getText().replace("$", ""));
    }

    public String getProductTitle() {
        return productTitle.getText();
    }

}
