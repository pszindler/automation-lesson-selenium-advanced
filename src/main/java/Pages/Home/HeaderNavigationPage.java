package Pages.Home;

import Exceptions.NoSuchMenuNameException;
import Models.MyShop.NavMenu;
import Pages.BasePage.BasePage;
import Pages.Category.CategoryPage;
import lombok.Getter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


public class HeaderNavigationPage extends BasePage {
    public HeaderNavigationPage(WebDriver driver) {
        super(driver);
        initMenu();
    }

    private final List<NavMenu> menu = new ArrayList<>();
    private final By DEEPER_INFO = By.xpath(".//a");
    @FindBy(xpath = "//ul[@data-depth='0']/li[@class='category']")
    private List<WebElement> categories;
    @Getter
    @FindBy(css = "popover sub-menu js-sub-menu collapse")
    private WebElement popOverSubMenu;
    @FindBy(css = ".ui-autocomplete-input")
    private WebElement searchInput;
    @FindBy(css = "li.ui-menu-item .product")
    private List<WebElement> productsAutocomplete;
    @FindBy(css = "[alt='TesterSii']")
    private WebElement myStoreHomePage;


    private void initMenu() {
        if (menu.isEmpty()) {
            setCategories();
            setSubCategories();
        }
    }

    private void setCategories() {
        categories.forEach(c -> menu.add
                (new NavMenu(
                        getCategoryName(c, DEEPER_INFO),
                        getAttribute(c, "href", DEEPER_INFO),
                        getDepthLevel(c, DEEPER_INFO),
                        c.getAttribute("id"),
                        null)));
    }

    private void setSubCategories() {
        List<NavMenu> subCategories = new ArrayList<>();
        menu.forEach(c -> {
            if (c.getParent() == null) {
                String parentId = c.getId();
                for (WebElement subcategory : getSubCategories(parentId)) {
                    subCategories.add(new NavMenu(
                            getAttribute(subcategory, "textContent", DEEPER_INFO).toUpperCase(),
                            getAttribute(subcategory, "href", DEEPER_INFO),
                            getDepthLevel(subcategory, DEEPER_INFO),
                            subcategory.getAttribute("id"),
                            c));
                }
            }
        });
        menu.addAll(subCategories);
    }

    public CategoryPage getCategoryPage(NavMenu navmenu) {
        if (navmenu.getParent() == null) {
            selectCategory(navmenu.getCategoryName());
        } else {
            WebElement categoryWebElement =
                    findCategoryNameElement(navmenu.getParent().getCategoryName());
            pointOnElement(categoryWebElement);
            if (!isElementVisible(popOverSubMenu)) {
                selectCategory(navmenu.getCategoryName());
            }
        }
        return new CategoryPage(driver);
    }

    public void goToCategory(String categoryName) {
        driver.get(menu.stream().filter(c -> c.getCategoryName().equalsIgnoreCase(categoryName))
                .findFirst()
                .orElseThrow(() -> new NoSuchMenuNameException(categoryName))
                .getHref());
    }

    public void goToRandomCategory() {
        driver.get(menu.get(new Random().nextInt(menu.size())).getHref());
    }

    public void selectCategory(String categoryName) {
        findCategoryNameElement(categoryName).click();
    }

    public List<NavMenu> getCategories(int levelDepth) {
        return menu.stream().filter(c -> c.getDepth() == levelDepth)
                .collect(Collectors.toList());
    }

    private String getCategoryName(WebElement element, By by) {
        return element.findElement(by).getText();
    }

    private String getAttribute(WebElement element, String attribute, By by) {
        return element.findElement(by).getAttribute(attribute).trim();
    }

    private Integer getDepthLevel(WebElement element, By by) {
        return Integer.parseInt(element.findElement(by).getAttribute("data-depth"));
    }

    public WebElement findCategoryNameElement(String categoryName) {
        String categoryId = menu.stream().filter(c -> c.getCategoryName()
                        .equalsIgnoreCase(categoryName))
                .findFirst().orElse(null).getId();
        return driver.findElement(By.id(categoryId));
    }

    private List<WebElement> getSubCategories(String categoryName) {
        List<WebElement> subCategories;
        By by = By.xpath("//li[@id='" + categoryName + "']/div/ul/li");
        try {
            subCategories = driver.findElements(by);
        } catch (NoSuchElementException ex) {
            throw new NoSuchMenuNameException(categoryName);
        }
        return subCategories;
    }

    public HeaderNavigationPage setSearchInput(String productName) {
        searchInput.sendKeys(productName);
        wait.until(ExpectedConditions.visibilityOfAllElements(productsAutocomplete));
        return this;
    }

    public HeaderNavigationPage submitSearch() {
        searchInput.sendKeys(Keys.ENTER);
        return this;
    }

    public List<String> getAutocompleteSuggestItems() {
        return productsAutocomplete.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void goToHomePage() {
        myStoreHomePage.click();
    }
}
