package tests.steps;

import aquality.selenium.template.configuration.Configuration;
import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;

import static aquality.selenium.browser.AqualityServices.getBrowser;
import static aquality.selenium.template.utilities.AllureHelper.takeScreenshot;

@UtilityClass
public class StepsMainPage {
    private static final String TEMPLATE_AUTHORIZATION = "http://%s:%s@";
    private static final String SUBSTRING_TO_REPLACE_TEMPLATE_AUTHORIZATION = "http://";

    @Step("Go to portal and authorization")
    public void logInMainPage() {
        getBrowser().goTo(Configuration.getStartUrl().replaceAll(SUBSTRING_TO_REPLACE_TEMPLATE_AUTHORIZATION,
                String.format(TEMPLATE_AUTHORIZATION, Configuration.getLogin(), Configuration.getPassword())));
        getBrowser().maximize();
        takeScreenshot();
    }
}
