package Models;

import lombok.Getter;
import org.openqa.selenium.Platform;
@Getter
public class WebDriverDTO {
    private final String browserVersion;
    private final String browserName;
    private final String platformName;

    public WebDriverDTO(String browserVersion, String browserName, Platform platformName) {
        this.browserVersion = browserVersion;
        this.browserName = browserName;
        this.platformName = String.valueOf(platformName);
    }
}
