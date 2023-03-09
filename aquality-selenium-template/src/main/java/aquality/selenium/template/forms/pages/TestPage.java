package aquality.selenium.template.forms.pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class TestPage extends Form {
    private final ILink linkAttachmentImage = getElementFactory().getLink(By.xpath("//*[contains(@class, 'thumbnail')]//parent::*"), "screenshot");

    public TestPage() {
        super(By.xpath("//*[contains(@class, 'panel-heading') and contains(text(), 'Common info')]"), "test info page");
    }

    public String getInfo(TestPageHeadingInfo heading) {
        String templateInfoLabel = "//*[contains(@class, 'list-group-item-heading') and contains(text(), '%s')]/following-sibling::*";
        return getElementFactory().getLabel(By.xpath(String.format(templateInfoLabel, heading.toString())), heading.toString()).getText();
    }

    public String getHrefScreenshot() {
        return linkAttachmentImage.getHref();
    }
}
