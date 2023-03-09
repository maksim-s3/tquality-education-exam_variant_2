package tests.steps;

import aquality.selenium.template.forms.pages.TestPage;
import aquality.selenium.template.models.Test;
import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import org.testng.asserts.SoftAssert;

import static aquality.selenium.template.forms.pages.TestPageHeadingInfo.*;
import static aquality.selenium.template.utilities.AllureHelper.takeScreenshot;

@UtilityClass
public class StepsTestPage {
    private static final String TEXT_TO_DELETE_FROM_HREF_SCREENSHOT = "data:image/png;base64,";
    private final TestPage testPage = new TestPage();
    private final SoftAssert softAssertion = new SoftAssert();

    @Step("Check all fields have the correct value. The screenshot corresponds to the one sent")
    public void assertInfoAboutTest(Test test, String encodedScreenshotAsStringBase64) {
        String encodedUploadedScreenshotAsStringBase64 = testPage.getHrefScreenshot().replaceAll(TEXT_TO_DELETE_FROM_HREF_SCREENSHOT, "");
        softAssertion.assertEquals(encodedUploadedScreenshotAsStringBase64, encodedScreenshotAsStringBase64, "Screenshots is not equals");
        softAssertion.assertEquals(testPage.getInfo(PROJECT_NAME), test.getProjectName(), "Project name is not equals");
        softAssertion.assertEquals(testPage.getInfo(TEST_NAME), test.getName(), "Test name is not equals");
        softAssertion.assertEquals(testPage.getInfo(TEST_METHOD_NAME), test.getMethod(), "Method name is not equals");
        softAssertion.assertEquals(testPage.getInfo(STATUS).toUpperCase(), test.getStatus(), "Status is not equals");
        softAssertion.assertEquals(testPage.getInfo(ENVIRONMENT), test.getEnv(), "Environment is not equals");
        softAssertion.assertEquals(testPage.getInfo(BROWSER), test.getBrowser(), "Browser name is not equals");
        takeScreenshot();
    }
}
