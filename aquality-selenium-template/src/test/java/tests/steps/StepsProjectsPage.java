package tests.steps;

import aquality.selenium.template.forms.pages.ProjectsPage;
import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import org.testng.Assert;

import static aquality.selenium.template.utilities.AllureHelper.takeScreenshot;

@UtilityClass
public class StepsProjectsPage {
    private final ProjectsPage projectsPage = new ProjectsPage();

    @Step("Check the projects page has opened")
    public void assertOpenProjectPage() {
        Assert.assertTrue(projectsPage.state().waitForDisplayed(), "Project page is not open");
        takeScreenshot();
    }

    @Step("Check correct option number in the footer")
    public void assertNumberVariant(int numberVariant) {
        Assert.assertEquals(projectsPage.getVersion(), numberVariant, "Version and variant does not equals");
        takeScreenshot();
    }

    @Step("Get {0} project id")
    public int getProjectId(String projectName) {
        return projectsPage.getProjectId(projectName);
    }

    @Step("Click on +Add")
    public void clickAddProject() {
        projectsPage.clickAddProject();
        takeScreenshot();
    }

    @Step("Check after updating the page the project {0} appeared")
    public void assertProjectAppeared(String nameProject) {
        Assert.assertTrue(projectsPage.isProjectExists(nameProject), "Project is not appeared");
        takeScreenshot();
    }

    @Step("Go to the {0} project page")
    public void goToProject(String projectName) {
        projectsPage.clickProject(projectName);
    }
}
