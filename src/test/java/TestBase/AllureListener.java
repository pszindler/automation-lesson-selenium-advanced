package TestBase;

import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.ByteArrayInputStream;

//TODO do rozkmnienia LifeCycleListener do robienia SS gdy testy sa failed
public class AllureListener implements TestLifecycleListener {
    public RemoteWebDriver driver;

    public AllureListener(RemoteWebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void beforeTestStop(TestResult result) {
        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            if (driver != null)
                Allure.addAttachment(result.getName(), new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        }
    }
}