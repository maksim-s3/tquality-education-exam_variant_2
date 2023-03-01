package aquality.selenium.template.forms.pages;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import aquality.selenium.template.custom_elements.TableTests;
import aquality.selenium.template.models.Test;
import org.openqa.selenium.By;
import java.util.List;

public class ProjectInfoPage extends Form {
    private By table = By.xpath("//*[contains(@class, 'table')]");
    private String templateLinkTest = "//td/a[contains(@href, '%d')]";
    private ILabel lblLoadingResults = getElementFactory().getLabel(By.xpath("//*[contains(@class, 'messi-content')]"), "loading results");
    private TableTests tableTests = getElementFactory().getCustomElement(TableTests::new, table, "table tests in project page");
    private ILabel lblAlertDanger = getElementFactory().getLabel(By.xpath("//td//*[contains(@class, 'alert-danger')]"), "alert there is no tests");

    public ProjectInfoPage() {
        super(By.xpath("//*[contains(@class, 'panel-body center')]"), "Project info page");
    }

    public List<Test> getTestsFromTable() {
        return tableTests.getAllTests();
    }

    public void waitForLoadingResults(){
        lblLoadingResults.state().waitForNotDisplayed();
    }

    public boolean isTestAppeared(Test test) {
        lblAlertDanger.state().waitForNotDisplayed();
        return tableTests.isTestInTable(test);
    }

    public void clickToTest(int testId) {
        ILink linkTest = getElementFactory().getLink(By.xpath(String.format(templateLinkTest, testId)), "link test");
        linkTest.click();
    }
}
