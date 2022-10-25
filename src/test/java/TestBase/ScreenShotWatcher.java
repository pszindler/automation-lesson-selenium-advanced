package TestBase;

import Helpers.ScreenShotCreator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ScreenShotWatcher implements TestWatcher {
    private static final Logger logger = LoggerFactory.getLogger(ScreenShotWatcher.class);
    private WebDriver driver;

    public ScreenShotWatcher(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable throwable) {
        logger.info("Test aborted " + context.getDisplayName());
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> optional) {
        logger.info("Test disabled " + context.getDisplayName() + optional);
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable throwable) {
        logger.info("Test failed " + context.getDisplayName());
        ScreenShotCreator.takeScreenShot(driver);
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        logger.info("Test successful " + extensionContext.getDisplayName());
    }

}
