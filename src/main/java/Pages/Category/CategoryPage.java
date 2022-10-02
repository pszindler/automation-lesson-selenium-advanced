package Pages.Category;

import Pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static Helpers.WebElementsHelper.isElementVisible;

public class CategoryPage extends BasePage {
    public CategoryPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@id='js-product-list-header']//h1[@class='h1']")
    private WebElement categoryName;
    @FindBy(css = "div#js-product-list-top p")
    private WebElement totalProductItems;
    @FindBy(css = "#search_filters")
    private WebElement filterMenu;

    public String getCategoryName() {
        return categoryName.getText();
    }

    public boolean isFilterMenuVisible() {
        return isElementVisible(filterMenu);
    }

    public int getTotalProductItems() {
        return Integer.parseInt(totalProductItems.getText().replaceAll("[^0-9]", ""));
    }

    @Override
    public String toString() {
        return "CategoryPage{" +
                "categoryName=" + categoryName +
                '}';
    }
}
