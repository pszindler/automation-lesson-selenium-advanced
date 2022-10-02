package TestBase;

import Config.AppPropertiesSingleton;
import Driver.DriverFactory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.util.logging.Level;

public class TestBase {
     protected WebDriver driver;

    @BeforeAll

    static void beforeAll() {
        AppPropertiesSingleton.getInstance();
        java.util.logging.Logger.getLogger("org.openqa.selenium").setLevel(Level.OFF);
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
     }

    @BeforeEach
    void setup() {
        driver = new DriverFactory().createInstance();
        driver.get("http://146.59.32.4/index.php");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }
}
