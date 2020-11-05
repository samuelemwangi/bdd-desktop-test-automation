package calculator.ui.utils;


import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.presentation.PresentationMode;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class ReportHelper {
    public static Reportable reportResult;

    public static void generateReport(String sub_report_tag) {
        Properties reportsProp =  new Properties();
        try {
            // Get config file
            Reader reader = Files.newBufferedReader(Paths.get("src/test/resources/reports.properties"));
            reportsProp.load(reader);

        } catch (IOException e) {
            e.printStackTrace();
        }

        File reportOutputDirectory = new File("target/report"+ File.separator + sub_report_tag);

        List<String> jsonFiles = new ArrayList<>();

        System.out.println("reportOutputDirectory" + reportOutputDirectory.getAbsolutePath());
        jsonFiles.add(reportOutputDirectory.getAbsolutePath() + File.separator + "cucumber.json");

        String buildNumber = reportsProp.getProperty("BuildNumber");
        String projectName = reportsProp.getProperty("ProjectName");

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        // optional configuration - check javadoc for details
        configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
        // do not make scenario failed when step has status SKIPPED
        configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));
        configuration.setBuildNumber(buildNumber);

        // additional metadata presented on main page
        configuration.addClassifications("Platform", reportsProp.getProperty("PlatformName"));
        configuration.addClassifications("Browser", reportsProp.getProperty("Browser"));
        configuration.addClassifications("Version",  reportsProp.getProperty("Release"));

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportResult = reportBuilder.generateReports();

    }
}
