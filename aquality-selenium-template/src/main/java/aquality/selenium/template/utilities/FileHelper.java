package aquality.selenium.template.utilities;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@UtilityClass
public class FileHelper {
    private static final String RESOURCES_PATH = "/src/test/resources/";
    private static final String LOG_FILE = "target/log/log.log";
    private static final String SHORT_LOG_FILE = "target/log/info.log";

    private static String getProjectBaseDir() {
        return System.getProperty("user.dir") != null ? System.getProperty("user.dir") : System.getProperty("project.basedir");
    }

    public static File getResourceFileByName(String fileName) {
        return Paths.get(getProjectBaseDir(), RESOURCES_PATH + fileName).toFile();
    }

    public static byte[] getLogAsBytes() {
        try {
            return Files.readAllBytes(Paths.get(LOG_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getInfoLogAsString() {
        try {
            return new String(Files.readAllBytes(Paths.get(SHORT_LOG_FILE)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
