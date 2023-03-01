package aquality.selenium.template.forms.pages;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class TestPage extends Form {
    private String templateInfoLabel = "//*[contains(@class, 'list-group-item-heading') and contains(text(), '%s')]/following-sibling::*";
    private ILink linkAttachmentImage = getElementFactory().getLink(By.xpath("//*[contains(@class, 'thumbnail')]//parent::*"), "link screenshot");
    public TestPage() {
        super(By.xpath("//*[contains(@class, 'panel-heading') and contains(text(), 'Common info')]"), "test info page");
    }

    public String getInfo(TestPageHeadingInfo heading) {
        ILabel label = getElementFactory().getLabel(By.xpath(String.format(templateInfoLabel, heading.toString())), "label "+heading.toString());
        return label.getText();
    }

    public String getSrcScreenshot(){
        return linkAttachmentImage.getHref();
    }
}
