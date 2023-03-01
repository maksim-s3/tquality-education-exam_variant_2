package aquality.selenium.template.rest_assured;

public enum RequestParams {
    VARIANT("variant"),
    PROJECT_ID("projectId"),
    SID("SID"),
    PROJECT_NAME("projectName"),
    TEST_NAME("testName"),
    METHOD_NAME("methodName"),
    ENV("env"),
    START_TIME("startTime"),
    BROWSER("browser"),
    TEST_ID("testId"),
    STATUS("status"),
    CONTENT("content");

    private final String title;

    RequestParams(String title) {
        this.title = title;
    }

    public String toString(){
        return title;
    }
}
