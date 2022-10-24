package TestBase;

import Config.AppPropertiesSingleton;
import Driver.DriverFactory;
import Models.WebDriverDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import static Helpers.UrlProvider.BASE_URL;

@ExtendWith(Listener.class)
public class TestBase {

    protected WebDriver driver;


    private static WebDriverDTO webDriverDTO;


    @BeforeAll
    public static void beforeAll() {
        AppPropertiesSingleton.getInstance();
    }

    @BeforeEach
    public void setup(WebDriver driver) {
        driver.get(BASE_URL);
    }

    @AfterEach
    public void teardown(WebDriver driver) {
        driver.quit();
    }


//    @AfterAll
//    public static void finallyTearDown() {
//        generateEnvironmentVariablesForAllure(webDriverDTO);
//    }

}

