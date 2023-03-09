package aquality.selenium.template.models;

public enum TypesContentAttachments {
    CONTENT_TYPE_IMAGE("image/png");

    private final String title;

    TypesContentAttachments(String title) {
        this.title = title;
    }

    @Override
    public String toString(){
        return title;
    }
}
