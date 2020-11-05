package calculator.ui.utils;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverService {

    private static long runDuration = 0;

    private static WindowsDriver<WindowsElement> winDriver = null;

    private static final Properties prop = new Properties();

    public DriverService() {
        try {
            // Get the config file
            Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/application.properties"));
            prop.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite(alwaysRun = true)
    public void setupDriver() {
        // Might get MalformedURLException
        try {

            runDuration = System.currentTimeMillis();
            // Set Capabilities
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

            // Set capability when app loads
            desiredCapabilities.setCapability("app", prop.getProperty("App"));

            //Remote URL to our JWP interface
            URL remoteUrl = new URL(prop.getProperty("RemoteUrl"));

            //Initiate driver with remote Url and desired capabilities
            winDriver = new WindowsDriver<WindowsElement>(remoteUrl, desiredCapabilities);

            //Set implicit Timeout for app loading
            winDriver.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("Timeout")), TimeUnit.SECONDS);

        } catch (Exception e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public WindowsDriver<WindowsElement> getDriver() {

        if (winDriver == null) {
            setupDriver();
        }
        return winDriver;
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownDriver() {

        if (winDriver != null) winDriver.quit();
        runDuration = System.currentTimeMillis() - runDuration;
        System.out.println("Test took " + runDuration + " ms");

        // Generate report
        ReportHelper.generateReport("");
    }


}
