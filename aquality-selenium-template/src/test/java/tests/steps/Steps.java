package tests.steps;

import aquality.selenium.template.configuration.Configuration;
import aquality.selenium.template.forms.pages.*;
import aquality.selenium.template.forms.pages.navigation.ItemsTopMenuNav;
import aquality.selenium.template.forms.pages.navigation.TopMenuNav;
import aquality.selenium.template.models.*;
import aquality.selenium.template.rest_assured.ReportPortalApiUtil;
import aquality.selenium.template.utilities.FileHelper;
import aquality.selenium.template.utilities.Listener;
import aquality.selenium.template.utilities.Random;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.testng.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static aquality.selenium.browser.AqualityServices.getBrowser;
import static aquality.selenium.template.utilities.AllureHelper.takeScreenshot;

public class Steps {
    private static final String FIELD_COOKIE_TOKEN = "token";
    private ProjectsPage projectsPage = new ProjectsPage();
    private ProjectInfoPage projectInfoPage = new ProjectInfoPage();
    private TopMenuNav topMenuNav = new TopMenuNav();
    private AddProjectForm addProjectForm = new AddProjectForm();
    private TestPage testPage = new TestPage();
    private String sid = Random.getRandomText(10, 15);
    private static String encodedScreenshotAsStringBase64;


    @Step("Request to the api to get a token according to the option number")
    public String getToken(int numberVariant) {
        return ReportPortalApiUtil.getToken(numberVariant);
    }

    @Step("Check the token generated")
    public void assertTokenNotNull(String token) {
        Assert.assertNotNull(token, "Token is null");
    }

    @Step("Pass authorization")
    public void logInPortal(String login, String password) {
        getBrowser().goTo(String.format(Configuration.getStartUrl(), login, password));
        getBrowser().maximize();
    }

    @Step("Check the projects page has opened")
    public void assertOpenProjectPage() {
        Assert.assertTrue(projectsPage.state().waitForDisplayed(), "project page is not open");
        takeScreenshot();
    }

    @Step("Add cookies with token")
    public void addCookies(String token) {
        Cookie cookie = new Cookie(FIELD_COOKIE_TOKEN, token);
        getBrowser().getDriver().manage().addCookie(cookie);
        getBrowser().refresh();
    }

    @Step("Check correct option number in the footer")
    public void assertNumberVariant(int numberVariant) {
        Assert.assertEquals(projectsPage.getVersion(), numberVariant, "version and variant does not equals");
        takeScreenshot();
    }

    @Step("Get project id")
    public int getProjectId(String projectName) {
        return projectsPage.getProjectId(projectName);
    }

    @Step("Go to the project page")
    public void goToProject(String projectName) {
        projectsPage.clickProject(projectName);
        projectInfoPage.state().waitForDisplayed();
        projectInfoPage.waitForLoadingResults();
        takeScreenshot();
    }

    @Step("Request to api to get a list of tests in JSON/XML format")
    public List<Test> getAllTestsProjectFromApi(int projectId) {
        return ReportPortalApiUtil.getAllTestsFromProject(projectId);
    }

    @Step("Get list tests from the page")
    public List<Test> getAllTestsProjectFromUi() {
        return projectInfoPage.getTestsFromTable();
    }

    @Step("Check the tests on the first page sorted by descending date")
    public void assertSortedByDescendingStartDate(List<Test> tests) {
        List<Test> sortedTests = tests.stream().sorted(Comparator.comparing(Test::getStartTime).reversed()).collect(Collectors.toList());
        Assert.assertEquals(tests, sortedTests, "tests in page project is not sorted");
    }

    @Step("Сheck the tests from the same page that the api request ")
    public void assertContainsTests(List<Test> listTestsFromUI, List<Test> listTestsFromAPI) {
        Assert.assertTrue(listTestsFromUI.stream().allMatch(test -> listTestsFromAPI.contains(test)));
    }

    @Step("Go back to the previous page in the browser (projects page)")
    public void backToProjectsPage() {
        topMenuNav.clickItemMenu(ItemsTopMenuNav.HOME);
        projectsPage.state().waitForDisplayed();
        takeScreenshot();
    }

    @Step("Click on +Add. In a new tab, enter the name of the project, save. Close the tab. Refresh the page")
    public void addNewProject(String nameNewProject) {
        String handleProjectPage = getBrowser().tabs().getCurrentTabHandle();
        projectsPage.clickAddProject();
        getBrowser().tabs().switchToLastTab();
        addProjectForm.setProjectName(nameNewProject);
        addProjectForm.clickSaveProject();
        assertProjectSaved(nameNewProject);
        getBrowser().tabs().closeTab();
        getBrowser().tabs().switchToTab(handleProjectPage);
        getBrowser().refresh();
        takeScreenshot();
    }

    @Step("After saving the project, a message about successful saving appeared.")
    private void assertProjectSaved(String nameProject) {
        Assert.assertTrue(addProjectForm.isProjectSaved(), "project is not added");
        takeScreenshot();
    }

    @Step("Check after updating the page the project appeared in the list")
    public void assertProjectAppeared(String nameProject) {
        Assert.assertTrue(projectsPage.isProjectExists(nameProject));
        takeScreenshot();
    }

    @Step("Add a test via the API (along with the log and screenshot of the current page).")
    public Test putTest(String projectName) {
        Test test = TestBuilder.build(Listener.getTestResult());
        test.setProjectName(projectName);
        test.setSid(sid);
        int testId = ReportPortalApiUtil.putTest(test);
        test.setId(testId);

        putLog(test);
        putScreenshot(test);

        test.setStatus(ResultStatuses.getRandomStatus());
        ReportPortalApiUtil.updateTest(test);
        Assert.assertTrue(projectInfoPage.isTestAppeared(test), "test is not appeared");
        takeScreenshot();
        return test;
    }

    @Step("Add log to test")
    private void putLog(Test test) {
        String log = FileHelper.getInfoLogAsString();
        ReportPortalApiUtil.putLog(test, log.substring(0, 3000));
    }

    @Step("Add screenshot to test")
    private void putScreenshot(Test test) {
        encodedScreenshotAsStringBase64 = getBrowser().getDriver().getScreenshotAs(OutputType.BASE64);
        System.out.println(encodedScreenshotAsStringBase64);
        UploadAttachment uploadScreenshot = new UploadAttachment(encodedScreenshotAsStringBase64, ContentTypeAttachment.CONTENT_TYPE_IMAGE.toString());
        ReportPortalApiUtil.putAttachment(test, uploadScreenshot);
    }

    @Step("Go to the created test page")
    public void goToTest(int testId) {
        projectInfoPage.clickToTest(testId);
        testPage.state().waitForDisplayed();
        takeScreenshot();
    }

    @Step("Check all fields have the correct value. The screenshot corresponds to the one sent")
    public void assertInfoAboutTest(Test test) {
        String encodedDownloadedScreenshotAsStringBase64 = testPage.getSrcScreenshot().replaceAll("data:image/png;base64,", "");
        Assert.assertEquals(encodedDownloadedScreenshotAsStringBase64, encodedScreenshotAsStringBase64, "screenshots is not equals");
        Assert.assertEquals(testPage.getInfo(TestPageHeadingInfo.PROJECT_NAME), test.getProjectName(), "project name is not equals");
        Assert.assertEquals(testPage.getInfo(TestPageHeadingInfo.TEST_NAME), test.getName(), "test name is not equals");
        Assert.assertEquals(testPage.getInfo(TestPageHeadingInfo.TEST_METHOD_NAME), test.getMethod(), "method name is not equals");
        Assert.assertEquals(testPage.getInfo(TestPageHeadingInfo.STATUS).toUpperCase(), test.getStatus(), "status is not equals");
        Assert.assertEquals(testPage.getInfo(TestPageHeadingInfo.ENVIRONMENT), test.getEnv(), "environment is not equals");
        Assert.assertEquals(testPage.getInfo(TestPageHeadingInfo.BROWSER), test.getBrowser(), "browser name is not equals");
        takeScreenshot();
    }
}
