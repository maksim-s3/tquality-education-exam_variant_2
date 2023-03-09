package aquality.selenium.template.custom_elements;

import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.Label;
import aquality.selenium.elements.interfaces.IElement;
import org.openqa.selenium.By;
import java.util.List;
import java.util.stream.Collectors;

public class TableTests extends Label {
    private final By th = By.xpath("//th");
    private final By tr = By.xpath("//tr[descendant::td]");
    private final By td = By.xpath("//td");

    public TableTests(By locator, String name, ElementState state) {
        super(locator, name, state);
    }

    public List<String> getHeaders() {
        return this.findChildElements(th, "heading", ElementType.LABEL)
                .stream().map(IElement::getText).collect(Collectors.toList());
    }

    public List<Label> getRows() {
        return this.findChildElements(tr, "row", ElementType.LABEL);
    }

    public List<Label> getCells(Label row) {
        return row.findChildElements(td, "cell", ElementType.LABEL);
    }

    public void clickToTest(int testId) {
        getElementFactory().getLink(By.xpath(String.format("//td/a[contains(@href, '%d')]", testId)), "link test").click();
    }
}
