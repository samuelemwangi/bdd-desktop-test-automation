package calculator.ui.drivers;

import calculator.ui.utils.ConfigHelper;
import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DesktopAppDriverService implements IDriverService {

    private static WindowsDriver<WindowsElement> winDriver = null;

    private static Properties driverConfigs;

    // Set default Implicit wait time,  override in constructor from configs
    private long implicitWaitTime = 5;

    // Set default explicit wait time, override in constructor from configs
    private int explicitWaitTime = 5;

    // Set Milliseconds to wait before startup
    private int timeToWaitAsAppStarts = 30000;


    static {
        try {
            driverConfigs = ConfigHelper.getConfigs("application");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DesktopAppDriverService() {

        // Get implicit wait time from configs, if null set to default
        String _implicitWaitTime = getPropertyValue("ImplicitWaitTime");
        implicitWaitTime = _implicitWaitTime != null
                ? Long.parseLong(_implicitWaitTime)
                : implicitWaitTime;

        // Get explicit wait time from configs, if null set to default
        String _explicitWaitTime = getPropertyValue("ExplicitWaitTime");
        explicitWaitTime = _explicitWaitTime != null
                ? Integer.parseInt(_explicitWaitTime)
                : explicitWaitTime;

        // Get explicit wait time from configs, if null set to default
        String _timeToWaitAsAppStarts = getPropertyValue("TimeToWaitAsAppStarts");
        timeToWaitAsAppStarts = _timeToWaitAsAppStarts != null
                ? Integer.parseInt(_timeToWaitAsAppStarts)
                : timeToWaitAsAppStarts;
    }

    @Override
    public void setupDriver() {
        // Might get a MalformedURLException
        try {

            // Set Capabilities
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

            // Set capability when app loads
            desiredCapabilities.setCapability("app", getPropertyValue("App"));

            //Remote URL to our JWP interface
            URL remoteUrl = new URL(getPropertyValue("RemoteUrl"));

            //Initiate driver with remote Url and desired capabilities
            winDriver = new WindowsDriver<>(remoteUrl, desiredCapabilities);

            //Set implicit Timeout for app loading
            winDriver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    @Override
    public WebDriver getDriver() {
        if (winDriver == null) {
            setupDriver();
        }
        return winDriver;
    }

    @Override
    public int getExplicitWaitTime() {
        return explicitWaitTime;
    }

    @Override
    public String getPropertyValue(String propertyKey) {
        return driverConfigs.getProperty(propertyKey);
    }

    @Override
    public void launchApp() {
        try {
            // sleep to allow app to load
            Thread.sleep(timeToWaitAsAppStarts);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeApp() {

    }

    @Override
    public void tearDownDriver() {
        if (winDriver != null) {
            winDriver.close();
            winDriver.quit();
        }

        winDriver = null;

    }
}
