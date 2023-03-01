package aquality.selenium.template.configuration;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.utilities.ISettingsFile;

import java.util.Map;

public class Configuration {

    private Configuration() {
    }

    public static String getStartUrl() {
        return Environment.getCurrentEnvironment().getValue("/startUrl").toString();
    }

    public static String getApiUrl() {
        return Environment.getCurrentEnvironment().getValue("/apiUrl").toString();
    }

    public static String getBrowserName(){
        return (String) AqualityServices.get(ISettingsFile.class).getValue("/browserName");
    }

    public static String getComputerName()
    {
        Map<String, String> env = System.getenv();
        if (env.containsKey("COMPUTERNAME"))
            return env.get("COMPUTERNAME");
        else if (env.containsKey("HOSTNAME"))
            return env.get("HOSTNAME");
        else
            return "Unknown Computer";
    }
}
