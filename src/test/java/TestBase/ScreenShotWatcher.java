package TestBase;

import Helpers.ScreenShotCreator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ScreenShotWatcher implements TestWatcher {
    private static final Logger logger = LoggerFactory.getLogger(ScreenShotWatcher.class);

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
        ScreenShotCreator.takeScreenShot(WebDriverFactoryStaticThreadLocal.getDriver());
    }

    @Override
    public void testSuccessful(ExtensionContext extensionContext) {
        logger.info("Test successful " + extensionContext.getDisplayName());
    }

}
