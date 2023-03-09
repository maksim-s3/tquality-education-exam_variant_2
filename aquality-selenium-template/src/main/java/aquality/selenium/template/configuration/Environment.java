package aquality.selenium.template.configuration;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import lombok.experimental.UtilityClass;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
class Environment {
    static ISettingsFile getCurrentEnvironment() {
        String envName = AqualityServices.get(ISettingsFile.class).getValue("/environment").toString();
        Path resourcePath = Paths.get("environment", envName, "config.json");
        return new JsonSettingsFile(resourcePath.toString());
    }
}
