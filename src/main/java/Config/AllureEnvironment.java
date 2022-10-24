package Config;

import Models.WebDriverDTO;
import com.google.common.collect.ImmutableMap;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class AllureEnvironment {
    public static void generateEnvironmentVariablesForAllure(WebDriverDTO webDriverDTO) {
        allureEnvironmentWriter(ImmutableMap.<String, String>builder()
                .put("BROWSER_NAME", System.getProperty("browserInUse"))
                .put("BROWSER_VERSION", webDriverDTO.getBrowserVersion())
                .put("HOST_URL", System.getProperty("appUrl"))
                .put("PLATFORM_NAME", webDriverDTO.getPlatformName())
                .put("APP_VERSION", "0.0.9-alpha")
                .build(), System.getProperty("user.dir")
                + "/allure-results/");
    }
}
