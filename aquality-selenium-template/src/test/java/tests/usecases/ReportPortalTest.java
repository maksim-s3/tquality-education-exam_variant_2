package tests.usecases;

import aquality.selenium.template.utilities.Random;
import io.qameta.allure.Description;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import aquality.selenium.template.models.Test;
import tests.BaseTest;
import tests.steps.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class ReportPortalTest extends BaseTest {
    private static final String NAME_FIELD_TOKEN_FOR_COOKIE = "token";

    @Description("Вариант 2 (UI + API)")
    @Parameters({"numberVariant", "projectName"})
    @org.testng.annotations.Test
    public void test(int numberVariant, String projectName) {
        String token = StepsAPI.getToken(numberVariant);
        Assert.assertNotNull(token, "Token has not been generated");

        StepsMainPage.logInMainPage();
        StepsProjectsPage.assertOpenProjectPage();
        getBrowser().getDriver().manage().addCookie(new Cookie(NAME_FIELD_TOKEN_FOR_COOKIE, token));
        getBrowser().refresh();
        StepsProjectsPage.assertNumberVariant(numberVariant);

        int projectId = StepsProjectsPage.getProjectId(projectName);
        StepsProjectsPage.goToProject(projectName);
        StepsProjectInfoPage.waitForLoadingTests();
        List<Test> listTestsFromAPI = StepsAPI.getAllTestsProjectFromApi(projectId);
        List<Test> listTestsFromUI = StepsProjectInfoPage.getAllTestsProjectFromUi();
        List<Test> sortedTestsFromUI = listTestsFromUI.stream().sorted(Comparator.comparing(Test::getStartTime).reversed()).collect(Collectors.toList());
        Assert.assertEquals(listTestsFromUI, sortedTestsFromUI, "Tests in page project is not sorted");
        Assert.assertTrue(listTestsFromAPI.containsAll(listTestsFromUI), "UI tests don't match API tests");

        getBrowser().goBack();
        String handleProjectPage = getBrowser().tabs().getCurrentTabHandle();
        StepsProjectsPage.clickAddProject();
        getBrowser().tabs().switchToLastTab();
        String handleAddProjectForm = getBrowser().tabs().getCurrentTabHandle();
        String nameNewProject = Random.getRandomText();
        StepsAddProjectForm.addNewProject(nameNewProject);
        StepsAddProjectForm.assertAlertProjectSaved();
        getBrowser().tabs().closeTab();
        getBrowser().tabs().switchToTab(handleProjectPage);
        Assert.assertFalse(getBrowser().tabs().getTabHandles().contains(handleAddProjectForm), "Tab didn't close");
        Assert.assertEquals(getBrowser().tabs().getCurrentTabHandle(), handleProjectPage, "The current page does not match with Projects Page");
        getBrowser().refresh();
        StepsProjectsPage.assertProjectAppeared(nameNewProject);

        StepsProjectsPage.goToProject(nameNewProject);
        Test test = StepsAPI.putTest(nameNewProject);
        StepsAPI.putLog(test);
        String encodedScreenshotAsStringBase64 = StepsAPI.putScreenshot(test);
        StepsAPI.updateTest(test);
        StepsProjectInfoPage.assertTestAppeared(test);

        StepsProjectInfoPage.goToTest(test.getId());
        StepsTestPage.assertInfoAboutTest(test, encodedScreenshotAsStringBase64);
    }
}
