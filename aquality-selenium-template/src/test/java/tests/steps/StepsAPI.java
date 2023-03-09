package tests.steps;

import aquality.selenium.template.models.*;
import aquality.selenium.template.rest_assured.ReportPortalApiUtil;
import aquality.selenium.template.utilities.FileHelper;
import aquality.selenium.template.utilities.Listener;
import aquality.selenium.template.utilities.Random;
import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.OutputType;
import java.util.Arrays;
import java.util.List;

import static aquality.selenium.browser.AqualityServices.getBrowser;
import static aquality.selenium.template.utilities.AllureHelper.takeScreenshot;

@UtilityClass
public class StepsAPI {
    private static final int BEGIN_INDEX_LOG = 0;
    private static final int END_INDEX_LOG = 3000;

    @Step("Request to the api to get a token according to the option number")
    public String getToken(int numberVariant) {
        return ReportPortalApiUtil.getToken(numberVariant);
    }


    @Step("Request to api to get a list of tests in JSON format")
    public List<Test> getAllTestsProjectFromApi(int projectId) {
        return ReportPortalApiUtil.getAllTestsFromProject(projectId);
    }

    @Step("Add a test via the API")
    public Test putTest(String projectName) {
        Test test = TestBuilder.build(Listener.getTestResult());
        test.setProjectName(projectName);
        int testId = ReportPortalApiUtil.putTest(test);
        test.setId(testId);
        takeScreenshot();
        return test;
    }

    @Step("Update test")
    public void updateTest(Test test) {
        List<ResultStatuses> values = Arrays.asList(ResultStatuses.values());
        test.setStatus(values.get(Random.getRandomIntNumber(values.size())).toString());
        ReportPortalApiUtil.updateTest(test);
        takeScreenshot();
    }

    @Step("Add log to test")
    public void putLog(Test test) {
        String log = FileHelper.getInfoLogAsString();
        ReportPortalApiUtil.putLog(test, log.substring(BEGIN_INDEX_LOG, END_INDEX_LOG));
    }

    @Step("Add screenshot to test and return encoded string in Base64")
    public String putScreenshot(Test test) {
        String encodedScreenshotAsStringBase64 = getBrowser().getDriver().getScreenshotAs(OutputType.BASE64);
        UploadAttachment uploadScreenshot = new UploadAttachment(encodedScreenshotAsStringBase64, TypesContentAttachments.CONTENT_TYPE_IMAGE.toString());
        ReportPortalApiUtil.putAttachment(test, uploadScreenshot);
        return encodedScreenshotAsStringBase64;
    }
}
