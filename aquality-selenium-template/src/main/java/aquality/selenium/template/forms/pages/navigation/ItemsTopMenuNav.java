package aquality.selenium.template.forms.pages.navigation;

public enum ItemsTopMenuNav {
    HOME("Home");

    private final String title;

    ItemsTopMenuNav(String title) {
        this.title = title;
    }

    public String toString(){
        return title;
    }
}
