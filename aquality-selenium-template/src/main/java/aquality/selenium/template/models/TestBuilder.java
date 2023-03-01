package aquality.selenium.template.models;

import aquality.selenium.template.configuration.Configuration;
import org.testng.ITestResult;

public class TestBuilder {

    public static Test build(ITestResult testResult) {
        Test test = new Test();
        test.setName(testResult.getTestContext().getName());
        test.setMethod(testResult.getMethod().getQualifiedName());
        test.setEnv(Configuration.getComputerName());
        test.setBrowser(Configuration.getBrowserName());
        return test;
    }
}
