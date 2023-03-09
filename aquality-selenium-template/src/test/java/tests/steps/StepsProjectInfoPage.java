package tests.steps;

import aquality.selenium.elements.Label;
import aquality.selenium.template.custom_elements.TableTests;
import aquality.selenium.template.custom_elements.TableTestsHeaders;
import aquality.selenium.template.forms.pages.ProjectInfoPage;
import aquality.selenium.template.models.Test;
import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static aquality.selenium.template.utilities.AllureHelper.takeScreenshot;

@UtilityClass
public class StepsProjectInfoPage {
    private final ProjectInfoPage projectInfoPage = new ProjectInfoPage();

    @Step("Get list tests from the page")
    public List<Test> getAllTestsProjectFromUi() {
        TableTests tableTests = projectInfoPage.getTableTests();
        List<String> headers = tableTests.getHeaders();
        List<Test> tests = new ArrayList<>();
        for (Label row : tableTests.getRows()) {
            List<Label> cells = tableTests.getCells(row);
            tests.add(new Test(
                    getTextFromCellByHeaderIndex(cells, headers, TableTestsHeaders.TEST_NAME),
                    getTextFromCellByHeaderIndex(cells, headers, TableTestsHeaders.TEST_METHOD),
                    getTextFromCellByHeaderIndex(cells, headers, TableTestsHeaders.LATEST_TEST_RESULT).toUpperCase(),
                    getTextFromCellByHeaderIndex(cells, headers, TableTestsHeaders.LATEST_TEST_START_TIME),
                    getTextFromCellByHeaderIndex(cells, headers, TableTestsHeaders.LATEST_TEST_END_TIME),
                    getTextFromCellByHeaderIndex(cells, headers, TableTestsHeaders.LATEST_TEST_DURATION))
            );
        }
        return tests;
    }

    private String getTextFromCellByHeaderIndex(List<Label> cells, List<String> headers, TableTestsHeaders header) {
        return cells.get(headers.indexOf(header.toString())).getText();
    }

    @Step("Go to the created test page")
    public void goToTest(int testId) {
        projectInfoPage.getTableTests().clickToTest(testId);
        takeScreenshot();
    }

    @Step("Wait for loading tests")
    public void waitForLoadingTests() {
        projectInfoPage.waitForLoadingResults();
        takeScreenshot();
    }

    @Step("Check the test appeared")
    public void assertTestAppeared(Test test) {
        projectInfoPage.waitForTestAppeared();
        Assert.assertTrue(getAllTestsProjectFromUi().stream().anyMatch(testFromUi -> testFromUi.getName().equals(test.getName())), "Test is not appeared");
        takeScreenshot();
    }
}
