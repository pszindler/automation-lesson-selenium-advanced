package Pages.Cart;

import Pages.BasePage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartProduct extends BasePage {
    public CartProduct(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    @FindBy(css = "[class] .product-line-info:nth-of-type(1)")
    private WebElement productTitle;
    @FindBy(css = "[class] .current-price")
    private WebElement productPrice;
    @FindBy(css = "strong")
    private WebElement subTotalPrice;
    @FindBy(css = "input[name='product-quantity-spin']")
    private WebElement quantity;
    @FindBy(css = ".cart-line-product-actions")
    private WebElement deleteIcon;

    public String getProductTitle() {
        return productTitle.getText();
    }

    public double getProductPrice() {
        return getProductPrice(productPrice);
    }

    public double getSubTotalPrice() {
        return getProductPrice(subTotalPrice);
    }

    public int getQuantity() {
        return Integer.parseInt(quantity.getAttribute("value"));
    }

    public void deleteItem() {
        WebElement deleteIcon = driver.findElement(By.cssSelector(".cart-items .cart-item:nth-of-type(1) .cart-line-product-actions"));
        deleteIcon.click();
        wait.until(ExpectedConditions.invisibilityOf(deleteIcon));
    }

    @Override
    public String toString() {
        return "CartProduct{" +
                "productTitle=" + productTitle.getText() +
                ", productPrice=" + productPrice.getText() +
                ", subTotalPrice=" + subTotalPrice.getText() +
                ", quantity=" + quantity.getAttribute("textContext") +
                '}';
    }
}
