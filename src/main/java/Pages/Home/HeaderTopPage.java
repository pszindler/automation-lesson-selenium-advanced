package Pages.Home;

import Pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HeaderTopPage extends BasePage {

    public HeaderTopPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".header .cart-products-count")
    private WebElement cartCounter;
    @FindBy(css = "div#_desktop_user_info .hidden-sm-down")
    private WebElement signInBtn;
    @FindBy(css = "div#_desktop_user_info .logout")
    private WebElement logoutBtn;
    @FindBy(css = "div#_desktop_user_info .account")
    private WebElement accountBtn;

    public int getCartCounter() {
        wait.until(ExpectedConditions.visibilityOf(cartCounter));
        return Integer.parseInt(cartCounter.getText().replaceAll("[^0-9]", ""));
    }

    public void goToLogIn() {
        signInBtn.click();
    }
}
