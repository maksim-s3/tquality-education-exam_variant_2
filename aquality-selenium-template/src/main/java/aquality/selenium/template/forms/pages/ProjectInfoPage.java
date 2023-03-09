package aquality.selenium.template.forms.pages;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import aquality.selenium.template.custom_elements.TableTests;
import org.openqa.selenium.By;

public class ProjectInfoPage extends Form {
    private final ILabel lblLoadingResults = getElementFactory().getLabel(By.xpath("//*[contains(@class, 'messi-content')]"), "loading results");
    private final ILabel lblAlertThereIsNoTests = getElementFactory().getLabel(By.xpath("//td//*[contains(@class, 'alert-danger')]"), "alert there is no tests");
    private final TableTests tableTests = getElementFactory().getCustomElement(TableTests::new, By.xpath("//*[contains(@class, 'table')]"), "table tests in project page");

    public ProjectInfoPage() {
        super(By.xpath("//*[contains(@class, 'panel-body center')]"), "Project info page");
    }

    public void waitForLoadingResults(){
        lblLoadingResults.state().waitForNotDisplayed();
    }

    public TableTests getTableTests(){
        return tableTests;
    }

    public void waitForTestAppeared() {
        lblAlertThereIsNoTests.state().waitForNotDisplayed();
    }
}
