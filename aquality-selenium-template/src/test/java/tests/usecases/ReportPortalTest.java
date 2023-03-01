package tests.usecases;

import aquality.selenium.template.utilities.Random;
import io.qameta.allure.Description;
import org.testng.annotations.Parameters;
import aquality.selenium.template.models.Test;
import tests.BaseTest;
import tests.steps.Steps;
import java.util.List;

public class ReportPortalTest extends BaseTest {
    private Steps steps = new Steps();

    @Description("Вариант 2 (UI + API)")
    @Parameters({"numberVariant", "projectName", "login", "password"})
    @org.testng.annotations.Test
    public void test(int numberVariant, String projectName,String login, String password) {
        String token = steps.getToken(numberVariant);
        steps.assertTokenNotNull(token);

        steps.logInPortal(login, password);
        steps.assertOpenProjectPage();
        steps.addCookies(token);
        steps.assertNumberVariant(numberVariant);

        int projectId = steps.getProjectId(projectName);
        steps.goToProject(projectName);
        List<Test> listTestsFromAPI = steps.getAllTestsProjectFromApi(projectId);
        List<Test> listTestsFromUI = steps.getAllTestsProjectFromUi();
        steps.assertSortedByDescendingStartDate(listTestsFromUI);
        steps.assertContainsTests(listTestsFromUI, listTestsFromAPI);

        steps.backToProjectsPage();
        String nameNewProject = Random.getRandomText(10, 15);
        steps.addNewProject(nameNewProject);
        steps.assertProjectAppeared(nameNewProject);

        steps.goToProject(nameNewProject);
        Test test = steps.putTest(nameNewProject);

        steps.goToTest(test.getId());
        steps.assertInfoAboutTest(test);
    }
}
