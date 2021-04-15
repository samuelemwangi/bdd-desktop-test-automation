package calculator.ui.pages;

import calculator.ui.drivers.IDriverService;
import calculator.ui.utils.SikuliHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.sikuli.script.Pattern;

public class BasePage {

    // Define how long to implicitly wait
    private static int ELEMENT_DISPLAY_TIMER = 10;


    // we need the driver service class
    public final IDriverService driverService;

    // Hold driver
    protected WebDriver driver;


    // We need sikuli for OCR
    protected final SikuliHelper sikuliHelper;


    public BasePage(IDriverService iDriverService) {
        driverService = iDriverService;
        driver = driverService.getDriver();
        sikuliHelper = new SikuliHelper();
        // Set Explicit Wait time
        ELEMENT_DISPLAY_TIMER = driverService.getExplicitWaitTime() > 0
                ? driverService.getExplicitWaitTime()
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
    protected Pattern findElement(String element) {
        // use sikuli
        return sikuliHelper.findElement(element);
    }

    // Click sikuli element
    protected void clickElement(Pattern element) {
        sikuliHelper.clickElement(element);
    }

    //  Web driver check if element exists
    protected boolean elementExists(By itemLocator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, ELEMENT_DISPLAY_TIMER);
            wait.until(ExpectedConditions.visibilityOfElementLocated(itemLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Sikuli check if element exists
    protected boolean elementExists(String element) {

        return sikuliHelper.IsElementPresent(element);
    }

}
