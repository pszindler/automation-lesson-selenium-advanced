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
    @FindBy(css = "input#quantity_wanted")
    private WebElement quantity;
    @FindBy(css = ".add-to-cart.btn.btn-primary")
    private WebElement addToCartButton;

    public String getProductTitle() {
        wait.until(ExpectedConditions.visibilityOf(productTitle));
        return productTitle.getText();
    }

    public ProductDetailsPage addToCart() {
        addToCartButton.click();
        return this;
    }

    public ProductDetailsPage setQuantity(String quantity) {
        this.quantity.sendKeys(quantity);
        return this;
    }
}
