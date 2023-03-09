package aquality.selenium.template.custom_elements;

public enum TableTestsHeaders {
    TEST_NAME("Test name"),
    TEST_METHOD("Test method"),
    LATEST_TEST_RESULT("Latest test result"),
    LATEST_TEST_START_TIME("Latest test start time"),
    LATEST_TEST_END_TIME("Latest test end time"),
    LATEST_TEST_DURATION("Latest test duration (H:m:s.S)");

    private final String title;

    TableTestsHeaders(String title) {
        this.title = title;
    }

    @Override
    public String toString(){
        return title;
    }
}
