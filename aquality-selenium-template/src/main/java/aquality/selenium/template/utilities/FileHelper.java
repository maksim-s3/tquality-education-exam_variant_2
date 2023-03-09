package aquality.selenium.template.utilities;

import lombok.experimental.UtilityClass;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@UtilityClass
public class FileHelper {
    private static final String LOG_FILE = "target/log/log.log";
    private static final String INFO_LOG_FILE = "target/log/info.log";

    public static byte[] getLogAsBytes() {
        try {
            return Files.readAllBytes(Paths.get(LOG_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getInfoLogAsString() {
        try {
            return new String(Files.readAllBytes(Paths.get(INFO_LOG_FILE)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
