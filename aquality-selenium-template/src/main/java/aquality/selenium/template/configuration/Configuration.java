package aquality.selenium.template.configuration;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;
import lombok.experimental.UtilityClass;
import java.util.Map;

@UtilityClass
public class Configuration {
    public static String getStartUrl() {
        return Environment.getCurrentEnvironment().getValue("/startUrl").toString();
    }

    public static String getApiUrl() {
        return Environment.getCurrentEnvironment().getValue("/apiUrl").toString();
    }

    public static String getBrowserName(){
        return AqualityServices.get(ISettingsFile.class).getValue("/browserName").toString();
    }

    public static String getLogin(){
        return Environment.getCurrentEnvironment().getValue("/login").toString();
    }

    public static String getPassword(){
        return Environment.getCurrentEnvironment().getValue("/password").toString();
    }

    public static String getComputerName() {
        final String windowsComputerName = "COMPUTERNAME";
        final String unixComputerName = "HOSTNAME";
        final String unknownComputer = "Unknown Computer";
        Map<String, String> env = System.getenv();
        if (env.containsKey(windowsComputerName)) {
            return env.get(windowsComputerName);
        }
        else {
            return env.getOrDefault(unixComputerName, unknownComputer);
        }
    }
}
