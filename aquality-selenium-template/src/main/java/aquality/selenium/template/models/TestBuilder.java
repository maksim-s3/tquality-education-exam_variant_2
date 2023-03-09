package aquality.selenium.template.models;

import aquality.selenium.template.configuration.Configuration;
import aquality.selenium.template.utilities.Random;
import org.testng.ITestResult;

public class TestBuilder {
    private static final String SID = Random.getRandomText();

    public static Test build(ITestResult testResult) {
        return new Test(
                testResult.getTestContext().getName(),
                testResult.getMethod().getQualifiedName(),
                Configuration.getComputerName(),
                Configuration.getBrowserName(),
                SID
        );
    }
}
