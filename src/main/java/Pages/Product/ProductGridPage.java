package Pages.Product;

import Pages.BasePage.BasePage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ProductGridPage extends BasePage {
    public ProductGridPage(WebDriver driver) {
        super(driver);
    }
    @Getter
    @FindBy(css = ".products.row > div")
    private List<WebElement> productItems;

    public int productItems() {
        return productItems.size();
    }

    public List<SingleProductFromGrid> getListOfProducts() {
        List<SingleProductFromGrid> products = new ArrayList<>();
        productItems.forEach(p -> products.add(new SingleProductFromGrid(driver, p)));
        return products;
    }

    public ProductGridPage clickOnProductByName(String productName) {
        driver.findElement(By.linkText(productName)).click();
        return this;
    }

}
