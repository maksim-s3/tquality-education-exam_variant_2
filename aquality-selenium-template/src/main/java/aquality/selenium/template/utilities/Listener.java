package aquality.selenium.template.utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {
    private static ITestResult testResult;

    @Override
    public void onTestStart(ITestResult result) {
        testResult = result;
    }

    @Override
    public void onTestFailure(ITestResult result) {
        AllureHelper.takeScreenshot();
        AllureHelper.takeLog();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        AllureHelper.takeLog();
    }

    public static ITestResult getTestResult() {
        return testResult;
    }
}
