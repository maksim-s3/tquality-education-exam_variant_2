package aquality.selenium.template.forms.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddProjectForm extends Form {
    private ITextBox txbProjectName = getElementFactory().getTextBox(By.id("projectName"), "field project name");
    private IButton btnSaveProject = getElementFactory().getButton(By.xpath("//*[contains(@class, 'btn btn-primary')]"), "save project");
    private ILabel lblAlertProjectSaved = getElementFactory().getLabel(By.xpath("//*[contains(@class, 'alert-success')]"), "alert success");

    public AddProjectForm() {
        super(By.id("addProjectForm"), "Add project form");
    }

    public void setProjectName(String name) {
        txbProjectName.clearAndType(name);
    }

    public void clickSaveProject() {
        btnSaveProject.click();
    }

    public boolean isProjectSaved() {
        return lblAlertProjectSaved.state().waitForDisplayed();
    }
}
