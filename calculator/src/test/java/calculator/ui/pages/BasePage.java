package calculator.ui.pages;

import calculator.ui.utils.DriverService;
import calculator.ui.utils.SikuliHelper;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;

import java.io.IOException;

public class BasePage  {

    // Define how long to implicitly wait
    private static int ELEMENT_DISPLAY_TIMER = 10;

    // we need the driver service class
    protected final DriverService driverService = new DriverService();

    // Hold driver
    protected final WindowsDriver<WindowsElement> driver = driverService.getDriver();

    // We need sikuli for OCR
    protected final SikuliHelper sikuliHelper = new SikuliHelper();

    public BasePage()  {
        // Set Implicit Wait time
        ELEMENT_DISPLAY_TIMER = driverService.implicitWaitTime > 0
                ? driverService.implicitWaitTime
                : ELEMENT_DISPLAY_TIMER;
    }

    // Implicit wait to find Element - Selenium Webdriver
    protected WebElement findElement(By itemLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, ELEMENT_DISPLAY_TIMER);
            wait.until(ExpectedConditions.visibilityOfElementLocated(itemLocator));
            return driver.findElement(itemLocator);

        } catch (Exception e) {
            throw e;
        }
    }

    // Find element using sikuli, wait implemented on called method
    protected Pattern findElement(String element){
        // use sikuli
        return sikuliHelper.findElement(element);
    }

    // Click sikuli element
    protected void clickElement(Pattern element){
        sikuliHelper.clickElement(element);
    }

    //  Web driver check if element exists
    protected  boolean elementExists(By itemLocator){
        try {
            WebDriverWait wait = new WebDriverWait(driver, ELEMENT_DISPLAY_TIMER);
            wait.until(ExpectedConditions.visibilityOfElementLocated(itemLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Sikuli check if element exists
    protected boolean elementExists(String element){

        return sikuliHelper.IsElementPresent(element);
    }

}
