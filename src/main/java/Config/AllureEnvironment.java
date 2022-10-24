package Config;

import Models.WebDriverDTO;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class AllureEnvironment {
    private static final Logger logger = LoggerFactory.getLogger(AllureEnvironment.class);

    public static void generateEnvironmentVariablesForAllure(WebDriverDTO webDriverDTO) {
        allureEnvironmentWriter(ImmutableMap.<String, String>builder()
                .put("BROWSER_NAME", System.getProperty("browserInUse"))
                .put("BROWSER_VERSION", webDriverDTO.getBrowserVersion())
                .put("HOST_URL", System.getProperty("appUrl"))
                .put("PLATFORM_NAME", webDriverDTO.getPlatformName())
                .put("APP_VERSION", "0.0.9-alpha")
                .build(), System.getProperty("user.dir")
                + "/allure-results/");
        logger.info("XML generated at {}", System.getProperty("user.dir") + "/allure-results/");
    }
}
