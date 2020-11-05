package calculator.ui.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AppUtilities {

    // Define how long to implicitly wait
    private static int ELEMENT_DISPLAY_TIMER = 30;

    // Hold driver
    protected WindowsDriver _driver;

    public AppUtilities() {

        // Get driver
        DriverService driverService = new DriverService();
        _driver = driverService.getDriver();
    }

    // Logic for implicit wait - Selenium Webdriver
    protected WebElement findElement(By itemLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(_driver, ELEMENT_DISPLAY_TIMER);
            wait.until(ExpectedConditions.visibilityOfElementLocated(itemLocator));
            return _driver.findElement(itemLocator);

        } catch (Exception e) {
            throw e;
        }
    }

}
