package calculator.ui.utils;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverService {

    private static WindowsDriver<WindowsElement> winDriver = null;

    private static Properties driverConfigs;

    public int implicitWaitTime;

    static {
        try {
            driverConfigs = ConfigHelper.getConfigs("application");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DriverService() {
        // Get implicit wait time from configs, if null set to 0
        implicitWaitTime = driverConfigs.getProperty("DisplayTimeout") != null
                ? Integer.parseInt(driverConfigs.getProperty("DisplayTimeout"))
                : 0;
    }


    public void setupDriver() {
        // Might get a MalformedURLException
        try {

            // Set Capabilities
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

            // Set capability when app loads
            desiredCapabilities.setCapability("app", driverConfigs.getProperty("App"));

            //Remote URL to our JWP interface
            URL remoteUrl = new URL(driverConfigs.getProperty("RemoteUrl"));

            //Initiate driver with remote Url and desired capabilities
            winDriver = new WindowsDriver<>(remoteUrl, desiredCapabilities);

            //Set implicit Timeout for app loading
            winDriver.manage().timeouts().implicitlyWait(Long.parseLong(driverConfigs.getProperty("Timeout")), TimeUnit.SECONDS);

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public WindowsDriver<WindowsElement> getDriver() {

        if (winDriver == null) {
            setupDriver();
        }
        return winDriver;
    }

    public void tearDownDriver() {

        if (winDriver != null) {
            winDriver.quit();
        }

        // Generate report
        ReportHelper.generateReport("");

    }


}
