package aquality.selenium.template.forms.pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import aquality.selenium.template.utilities.StringUtil;
import org.openqa.selenium.By;

public class ProjectsPage extends Form {
    private static final String NAME_OF_PROJECT_ID_PARAM_IN_HREF = "projectId";
    private final ILabel lblTetPortalVersion = getElementFactory().getLabel(By.xpath("//*[contains(@class, 'footer-text')]//*"), "version portal");
    private final String templateButtonProject = "//*[contains(@class, 'list-group-item') and contains(text(), '%s')]";
    private final IButton btnAddProject = getElementFactory().getButton(By.xpath("//*[contains(@class, 'btn btn-xs btn-primary pull-right')]"), "add project");

    public ProjectsPage() {
        super(By.xpath("//*[@class='panel-heading' and contains(text(), 'Available projects')]"), "project page");
    }

    public int getVersion() {
        lblTetPortalVersion.getJsActions().scrollIntoView();
        return StringUtil.getIntFromString(lblTetPortalVersion.getText());
    }

    public void clickProject(String projectName) {
        getElementFactory().getButton(By.xpath(String.format(templateButtonProject, projectName)), "project name").click();
    }

    public int getProjectId(String projectName) {
        String href = getElementFactory().getLink(By.xpath(String.format(templateButtonProject, projectName)), "project name").getHref();
        return StringUtil.getIntFromString(href.substring(href.indexOf(NAME_OF_PROJECT_ID_PARAM_IN_HREF)));
    }

    public void clickAddProject() {
        btnAddProject.click();
    }

    public boolean isProjectExists(String projectName) {
        return getElementFactory().getLabel(By.xpath(String.format(templateButtonProject, projectName)), "project name").state().waitForDisplayed();
    }
}
