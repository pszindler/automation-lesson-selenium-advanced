package TestBase;

import Config.AppPropertiesSingleton;
import Driver.DriverFactory;
import Helpers.ScreenShotCreator;
import Models.WebDriverDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static Config.AllureEnvironment.generateEnvironmentVariablesForAllure;
import static Helpers.UrlProvider.BASE_URL;

public class TestBase {
    private static final Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected WebDriver driver;
    private static WebDriverDTO webDriverDTO;


    @BeforeAll
    public static void beforeAll() {
        AppPropertiesSingleton.getInstance();
    }

    @BeforeEach
    public void setup() {
        driver = new DriverFactory().createInstance();
        logger.info("Driver initiated properly");
        if (webDriverDTO == null) {
            Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
            webDriverDTO = new WebDriverDTO(caps.getBrowserVersion(), caps.getBrowserName(), caps.getPlatformName());
            logger.info("Environment information's has been set for Allure Report.");
        }
        driver.get(BASE_URL);
        logger.info("Driver reach base url: {}", BASE_URL);
    }

    @AfterEach
    public void teardown() {
        ScreenShotCreator.takeScreenShot(driver);
        driver.quit();
        logger.info("Driver closed properly");
    }

    @AfterAll
    public static void finallyTearDown() {
        generateEnvironmentVariablesForAllure(webDriverDTO);
        logger.info("Environment xml has been loaded");
    }
}

