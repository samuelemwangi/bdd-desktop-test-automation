package calculator.ui.utils;

import io.appium.java_client.windows.WindowsDriver;
import org.openqa.selenium.Platform;
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

    private static WindowsDriver driver = null;

    public static Properties prop = new Properties();

    public String ProjectName = "";

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
            driver = new WindowsDriver(remoteUrl, desiredCapabilities);

            //Set implicit Timeout for app loading
            driver.manage().timeouts().implicitlyWait(Long.parseLong(prop.getProperty("Timeout")), TimeUnit.SECONDS);

            // Project Name
            ProjectName = prop.getProperty("ProjectName");

        } catch (Exception e) {

            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public WindowsDriver getDriver() {

        if (driver == null) {
            setupDriver();
        }
        return driver;
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownDriver() {

        if (driver != null) driver.quit();
        runDuration =  System.currentTimeMillis() - runDuration;
        System.out.println("Test took " + runDuration +" ms");
    }


}
