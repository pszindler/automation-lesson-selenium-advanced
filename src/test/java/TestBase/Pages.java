package TestBase;

import Pages.BasePage.BasePage;
import org.openqa.selenium.support.PageFactory;

public abstract class Pages extends TestBase {

    public <T extends BasePage> T at(Class<T> pageType) {
        return PageFactory.initElements(driver, pageType);
    }
}
