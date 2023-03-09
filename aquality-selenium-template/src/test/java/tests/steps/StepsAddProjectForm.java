package tests.steps;

import aquality.selenium.template.forms.pages.AddProjectForm;
import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import org.testng.Assert;

import static aquality.selenium.template.utilities.AllureHelper.takeScreenshot;

@UtilityClass
public class StepsAddProjectForm {
    private final AddProjectForm addProjectForm = new AddProjectForm();

    @Step("Enter the name of the project, save")
    public void addNewProject(String nameNewProject) {
        addProjectForm.setProjectName(nameNewProject);
        takeScreenshot();
        addProjectForm.clickSaveProject();
    }

    @Step("After saving the project, a message about successful saving appeared")
    public void assertAlertProjectSaved() {
        Assert.assertTrue(addProjectForm.isProjectSaved(), "The message about saving the project did not appear");
        takeScreenshot();
    }
}
