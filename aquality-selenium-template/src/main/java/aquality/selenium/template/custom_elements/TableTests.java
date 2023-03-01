package aquality.selenium.template.custom_elements;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.Label;
import aquality.selenium.template.models.Test;
import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.List;

public class TableTests extends Label {
    private By th = By.xpath("//th");
    private By tr = By.xpath("//tr[descendant::td]");
    private By td = By.xpath("//td");

    public TableTests(By locator, String name, ElementState state) {
        super(locator, name, state);
    }

    public List<Test> getAllTests() {
        List<Test> tests = new ArrayList<>();
        List<String> headings = getHeadings();
        List<Label> rows = getRows();
        for (Label row : rows) {
            List<Label> cells = row.findChildElements(td, "cell", Label.class);
            Test test = new Test();
            test.setName(cells.get(headings.indexOf(TableTestsHeadings.TEST_NAME.toString())).getText());
            test.setMethod(cells.get(headings.indexOf(TableTestsHeadings.TEST_METHOD.toString())).getText());
            test.setStatus(cells.get(headings.indexOf(TableTestsHeadings.LATEST_TEST_RESULT.toString())).getText().toUpperCase());
            test.setStartTime(cells.get(headings.indexOf(TableTestsHeadings.LATEST_TEST_START_TIME.toString())).getText());
            test.setEndTime(cells.get(headings.indexOf(TableTestsHeadings.LATEST_TEST_END_TIME.toString())).getText());
            test.setDuration(cells.get(headings.indexOf(TableTestsHeadings.LATEST_TEST_DURATION.toString())).getText());
            tests.add(test);
        }
        return tests;
    }

    public boolean isTestInTable(Test test) {
        List<String> headings = getHeadings();
        List<Label> rows = getRows();
        if (rows.isEmpty()) {
            return false;
        }
        for (Label row : rows) {
            List<Label> cells = row.findChildElements(td, "cell", Label.class);
            if (cells.get(headings.indexOf(TableTestsHeadings.TEST_NAME.toString())).getText().contains(test.getName()))
                return true;
        }
        return false;
    }

    private List<String> getHeadings() {
        List<String> headings = new ArrayList<>();
        for (Label heading : this.findChildElements(th, "heading", Label.class)) {
            headings.add(heading.getText());
        }
        return headings;
    }

    private List<Label> getRows() {
        return this.findChildElements(tr, "row", Label.class);
    }
}
