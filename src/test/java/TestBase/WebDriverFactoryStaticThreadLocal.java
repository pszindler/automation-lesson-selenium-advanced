package TestBase;

import Driver.DriverFactory;
import org.openqa.selenium.WebDriver;

public class WebDriverFactoryStaticThreadLocal {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver() {
        driver.set(new DriverFactory().createInstance());
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeBrowser() {
        driver.get().quit();
        driver.remove();
    }


}
