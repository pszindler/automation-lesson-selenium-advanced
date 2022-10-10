package Pages.Product;

import Models.MyShop.Product;
import Pages.BasePage.BasePage;
import Pages.Cart.SummaryPopupPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Helpers.WebElementsHelper.isElementVisible;

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
    @FindBy(css = "button[name='submitCustomizedData']")
    private WebElement saveCustomizationBtn;
    @FindBy(css = "textarea[name='textField1']")
    private WebElement customizeText;
    @Getter
    List<Product> productCarts = new ArrayList<>();

    public String getProductTitle() {
        wait.until(ExpectedConditions.visibilityOf(productTitle));
        return productTitle.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public Product addToCart(int userQuantity) {
        SummaryPopupPage popupPage = new SummaryPopupPage(driver);
        setQuantity(userQuantity);
        checkIfAddToCartButtonIsClickable();
        addToCartButton.click();
        popupPage.waitForPopUp();
        return new Product(getProductTitle(), getProductPrice());
    }

    public void setQuantity(int userQuantity) {
        this.quantity.clear();
        this.quantity.sendKeys(String.valueOf(userQuantity));
    }

    public int setRandomQuantity() {
        this.quantity.clear();
        int randomQuantity = new Random().ints(1, 6).findFirst().getAsInt();
        this.quantity.sendKeys(String.valueOf(randomQuantity));
        return randomQuantity;
    }

    public void checkIfAddToCartButtonIsClickable() {
        if (isElementVisible(saveCustomizationBtn)) {
            customizeText.sendKeys("I LOVE MOM");
            saveCustomizationBtn.click();
        }
    }

}

