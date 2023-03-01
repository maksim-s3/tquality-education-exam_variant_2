package aquality.selenium.template.forms.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import aquality.selenium.template.utilities.StringUtil;
import org.openqa.selenium.By;

public class ProjectsPage extends Form {
    private ILabel lblTetPortalVersion = getElementFactory().getLabel(By.xpath("//*[contains(@class, 'footer-text')]//*"), "label version portal");
    private String templateButtonProject = "//*[contains(@class, 'list-group-item') and contains(text(), '%s')]";
    private IButton btnAddProject = getElementFactory().getButton(By.xpath("//*[contains(@class, 'btn btn-xs btn-primary pull-right')]"), "add project");

    public ProjectsPage() {
        super(By.xpath("//*[@class='panel-heading' and contains(text(), 'Available projects')]"), "project page");
    }

    public int getVersion() {
        return StringUtil.getIntFromString(lblTetPortalVersion.getText());
    }

    public void clickProject(String projectName) {
        IButton btnProject = getElementFactory().getButton(By.xpath(String.format(templateButtonProject, projectName)), "project " + projectName);
        btnProject.click();
    }

    public int getProjectId(String projectName) {
        ILink linkProject = getElementFactory().getLink(By.xpath(String.format(templateButtonProject, projectName)), "project " + projectName);
        String href = linkProject.getHref();
        return StringUtil.getIntFromString(href.substring(href.indexOf("projectId")));
    }

    public void clickAddProject() {
        btnAddProject.click();
    }

    public boolean isProjectExists(String projectName) {
        ILabel lblProject = getElementFactory().getLabel(By.xpath(String.format(templateButtonProject, projectName)), "name project: " + projectName);
        return lblProject.state().waitForDisplayed();
    }
}
