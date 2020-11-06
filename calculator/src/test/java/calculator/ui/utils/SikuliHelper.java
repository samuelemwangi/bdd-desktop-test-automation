package calculator.ui.utils;

import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

public class SikuliHelper {

    // Set default , can be overridden in constructor
    private static int ELEMENT_DISPLAY_TIMER = 10;

    // Hold Images Base Path
    private static String IMAGE_BASE_PATH;

    // Hold Minimum Allowed Score
    private static double MIN_ALLOWED_SCORE = 80.0;

    // Get Sikuli Configs
    private static Properties sikuliConfigs;

    // Initialize screen
    private static final Screen screen = new Screen();

    private Pattern patternTargetImage;

    static {
        try {
            sikuliConfigs = ConfigHelper.getConfigs("sikuli");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SikuliHelper() {
        try {

            // Get sikuli pattern images base path
            IMAGE_BASE_PATH = new File(sikuliConfigs.getProperty("PatternImages")).getAbsolutePath();

            // Override  Display Time
            ELEMENT_DISPLAY_TIMER = sikuliConfigs.getProperty("DisplayTimer") != null
                    ? Integer.parseInt(sikuliConfigs.getProperty("DisplayTimer"))
                    : ELEMENT_DISPLAY_TIMER;

            // Override MIN ALLOWED SCORE
            MIN_ALLOWED_SCORE = sikuliConfigs.getProperty("MinimumScore") != null
                    ? Double.parseDouble(sikuliConfigs.getProperty("MinimumScore"))
                    : MIN_ALLOWED_SCORE;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean IsElementPresent(String targetImage) {
        Match actualResult = screen.exists(IMAGE_BASE_PATH + File.separator + targetImage, ELEMENT_DISPLAY_TIMER);
        if (actualResult == null) {
            return false;
        } else {
            
            double actualScore = actualResult.getScore() * 100.0;

            System.out.println("Score for '" + targetImage + "' ==> " + actualScore + " ");

            actualScore = BigDecimal.valueOf(actualScore).setScale(2, RoundingMode.HALF_UP).doubleValue();

            return actualScore >= MIN_ALLOWED_SCORE;
        }
    }

    public Pattern findElement(String targetImage) {
        try {
            return new Pattern(IMAGE_BASE_PATH + File.separator + targetImage);
        } catch (Exception e) {
            throw e;
        }

    }

    public void clickElement(Pattern element) {
        try {
            screen.wait(element.similar(0.90), ELEMENT_DISPLAY_TIMER).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
