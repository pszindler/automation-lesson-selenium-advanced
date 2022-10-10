package Pages.Home;

import Pages.BasePage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage {
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "section input[name='email']")
    private WebElement emailInput;
    @FindBy(css = "input[name='password']")
    private WebElement passwordInput;
    @FindBy(css = "button#submit-login")
    private WebElement signInBtn;
    private final String signInLink = "http://146.59.32.4/index.php?controller=authentication&back=my-account";

    public void signIn(String email, String password) {
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        signInBtn.click();
    }

}
