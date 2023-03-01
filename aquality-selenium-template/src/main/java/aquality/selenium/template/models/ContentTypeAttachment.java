package aquality.selenium.template.models;

public enum ContentTypeAttachment {
    CONTENT_TYPE_IMAGE("image/png"),
    CONTENT_TYPE_TEXT("text/html");

    private final String title;

    ContentTypeAttachment(String title) {
        this.title = title;
    }

    public String toString(){
        return title;
    }
}
