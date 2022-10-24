package Pages.Cart;

import Pages.BasePage.BasePage;
import Pages.Product.ProductDetailsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SummaryPopupPage extends BasePage {
    public SummaryPopupPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".product-quantity strong")
    private WebElement quantity;
    @FindBy(css = ".product-name")
    private WebElement productName;
    @FindBy(css = ".cart-content .cart-products-count")
    private WebElement totalItemsInCartText;
    @FindBy(css = ".subtotal.value")
    private WebElement subtotalValue;
    @FindBy(css = ".modal-body .product-price")
    private WebElement productPrice;
    @FindBy(css = ".btn.btn-secondary")
    private WebElement continueShoppingBtn;
    @FindBy(css = ".cart-content-btn .btn-primary")
    private WebElement proceedToCheckoutBtn;
    @FindBy(css = "div#blockcart-modal .modal-content")
    private WebElement modalContent;

    public double getSubTotal() {
        return Double.parseDouble(subtotalValue.getText()
                .replace("$", ""));
    }

    public double getProductPrice() {
        return Double.parseDouble(productPrice.getText()
                .replace("$", ""));
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getTotalItemsInCartText() {
        return totalItemsInCartText.getText();
    }

    public int getProductQuantity() {
        return Integer.parseInt(quantity.getText().replaceAll("[^0-9]", ""));
    }

    public ProductDetailsPage continueShopping() {
        wait.until(ExpectedConditions.visibilityOf(continueShoppingBtn));
        continueShoppingBtn.click();
        return new ProductDetailsPage(driver);
    }

    public CartPage proceedToCheckoutOrder() {
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutBtn));
        proceedToCheckoutBtn.click();
        return new CartPage(driver);
    }

    public void waitForPopUp() {
        wait.until(ExpectedConditions.visibilityOf(modalContent));
    }
}
