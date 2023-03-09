package aquality.selenium.template.rest_assured;

public enum RequestParams {
    VARIANT("variant"),
    PROJECT_ID("projectId"),
    SID("SID"),
    PROJECT_NAME("projectName"),
    TEST_NAME("testName"),
    METHOD_NAME("methodName"),
    ENV("env"),
    BROWSER("browser"),
    TEST_ID("testId"),
    STATUS("status"),
    CONTENT("content");

    private final String title;

    RequestParams(String title) {
        this.title = title;
    }

    @Override
    public String toString(){
        return title;
    }
}
