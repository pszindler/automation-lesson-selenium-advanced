package Pages.BasePage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage {

    static final Duration TIMEOUT = Duration.ofSeconds(Integer.parseInt(System.getProperty("webElementTimeout")));
    static final Duration POOLING = Duration.ofMillis(Integer.parseInt(System.getProperty("webElementPooling")));

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    public BasePage(WebDriver driver) {
        initDriver(driver);
        PageFactory.initElements(driver, this);
    }

    public BasePage(WebDriver driver, WebElement element) {
        initDriver(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(element), this);
    }

    private void initDriver(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, TIMEOUT, POOLING);
        actions = new Actions(driver);
    }

    public void pointOnElement(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public <T> T getRandomWebElement (List<T> list) {
        return list.get(new Random().nextInt(list.size()));
    }

    public boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public double getProductPrice(WebElement webElement) {
        return Double.parseDouble(webElement.getText().replace("$", ""));
    }

    public String getTextAfterColon(WebElement webElement) {
        return webElement.getText().split(":")[1].strip();
    }



}
