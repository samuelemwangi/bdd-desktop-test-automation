package calculator.ui.utils;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigHelper {

    public static Properties getConfigs(String propType) throws IOException {

        try {
            Properties prop = new Properties();

            // Expect properties files in this folder
            String propFilesPath = "src/test/resources/";

            // Get the config file
            Reader reader = Files.newBufferedReader(Paths.get(propFilesPath + propType + ".properties"));
            prop.load(reader);

            return prop;
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

}
