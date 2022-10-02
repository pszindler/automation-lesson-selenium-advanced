package Pages.Product;

import Pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetailsPage extends BasePage {
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".h1")
    private WebElement productTitle;
    @FindBy(css = "[itemprop='price']")
    private WebElement productPrice;

    public String getProductTitle() {
        wait.until(ExpectedConditions.visibilityOf(productTitle));
        return productTitle.getText();
    }
}
