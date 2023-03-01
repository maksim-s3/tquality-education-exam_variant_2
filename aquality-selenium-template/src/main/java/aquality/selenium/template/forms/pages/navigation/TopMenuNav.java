package aquality.selenium.template.forms.pages.navigation;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class TopMenuNav extends Form {
    private String templateItemMenu = "//*[contains(@class, 'breadcrumb')]//a[contains(text(), '%s')]";

    public TopMenuNav() {
        super(By.xpath("//*[contains(@class, 'breadcrumb')]"), "top panel navigation");
    }

    public void clickItemMenu(ItemsTopMenuNav item) {
        IButton btnItemMenu = getElementFactory().getButton(
                By.xpath(String.format(templateItemMenu, item.toString())), "Item menu: " + item.toString());
        btnItemMenu.state().waitForDisplayed();
        btnItemMenu.click();
    }
}
