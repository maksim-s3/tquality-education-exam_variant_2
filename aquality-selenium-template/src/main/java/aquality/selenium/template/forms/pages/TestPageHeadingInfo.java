package aquality.selenium.template.forms.pages;

public enum TestPageHeadingInfo {
    PROJECT_NAME("Project name"),
    TEST_NAME("Test name"),
    TEST_METHOD_NAME("Test method name"),
    STATUS("Status"),
    ENVIRONMENT("Environment"),
    BROWSER("Browser");

    private final String title;

    TestPageHeadingInfo(String title) {
        this.title = title;
    }

    public String toString(){
        return title;
    }
}
