package Helpers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class WebElementsHelper {

    public static boolean isElementVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
