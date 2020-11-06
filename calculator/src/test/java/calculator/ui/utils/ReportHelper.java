package calculator.ui.utils;


import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.json.support.Status;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import net.masterthought.cucumber.presentation.PresentationMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class ReportHelper {
    public static Reportable reportResult;

    // Get Report Configs
    private static Properties reportConfigs;

    static {
        try {
            reportConfigs = ConfigHelper.getConfigs("reports");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateReport(String sub_report_tag) {

        File reportOutputDirectory = new File("target/report"+ File.separator + sub_report_tag);

        List<String> jsonFiles = new ArrayList<>();

        jsonFiles.add(reportOutputDirectory.getAbsolutePath() + File.separator + "cucumber.json");

        String buildNumber = reportConfigs.getProperty("BuildNumber");
        String projectName = reportConfigs.getProperty("ProjectName");

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        // optional configuration - check javadoc for details
        configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
        // do not make scenario failed when step has status SKIPPED
        configuration.setNotFailingStatuses(Collections.singleton(Status.SKIPPED));

        configuration.setBuildNumber(buildNumber);

        // additional metadata presented on main page
        configuration.addClassifications("Platform", reportConfigs.getProperty("PlatformName"));
        configuration.addClassifications("Version",  reportConfigs.getProperty("Release"));

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportResult = reportBuilder.generateReports();

    }
}
