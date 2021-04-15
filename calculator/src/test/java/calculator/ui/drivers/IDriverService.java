package calculator.ui.drivers;

import org.openqa.selenium.WebDriver;

public interface IDriverService {
    void setupDriver();

    WebDriver getDriver();

    int getExplicitWaitTime();

    String getPropertyValue(String propertyKey);

    void launchApp();

    void closeApp();

    void tearDownDriver();

}
