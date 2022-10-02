package Pages.Product;

import Pages.BasePage.BasePage;
import lombok.Getter;
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

    public List<ProductPage> getListOfProducts() {
        List<ProductPage> products = new ArrayList<>();
        productItems.forEach(p -> products.add(new ProductPage(driver, p)));
        return products;
    }

}
