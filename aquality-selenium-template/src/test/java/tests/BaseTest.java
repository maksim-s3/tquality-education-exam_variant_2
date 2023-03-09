package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.template.utilities.Listener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import static aquality.selenium.browser.AqualityServices.getBrowser;

@Listeners(Listener.class)
public abstract class BaseTest {

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            getBrowser().quit();
        }
    }
}
