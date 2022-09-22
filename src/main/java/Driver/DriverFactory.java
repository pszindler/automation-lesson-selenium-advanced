package Driver;

import Driver.Manager.*;
import Exceptions.BrowserNotSupportedException;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
    public WebDriver createInstance (String browserFromConfig) {
        WebDriver driver;
        BrowserTypes browser = BrowserTypes.valueOf(browserFromConfig.toUpperCase());

        driver = switch (browser) {
            case CHROME -> new ChromeDriverManager().createDriver();
            case FIREFOX -> new FirefoxDriverManager().createDriver();
            case IE -> new IEDriverManager().createDriver();
            case SAFARI -> new SafariDriverManager().createDriver();
            case EDGE -> new EdgeDriverManager().createDriver();
            default -> throw new BrowserNotSupportedException(browser);
        };
        return driver;
    }
}
