package Helpers;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotCreator {
    private static final Logger logger = LoggerFactory.getLogger(ScreenShotCreator.class);

    public static void takeScreenShot(WebDriver driver) {
        try {
            if (driver != null) {
                File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Allure.addAttachment(fileNameGenerator(), FileUtils.openInputStream(screenshotFile));
                logger.info("Screenshot added to report");
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Could not make a screenshot");
            throw new RuntimeException("Could not make a screenshot");
        }
    }

    private static String fileNameGenerator() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd_MM_yyyy");
        return "selenium_" + formatter.format(new Date()) + "_" + RandomStringUtils.randomAlphanumeric(4) + ".png";
    }
}