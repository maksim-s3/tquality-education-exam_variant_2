package aquality.selenium.template.utilities;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtil {
    private static final String REGEX_SELECT_NUMBERS = "[^0-9]+";

    public static int getIntFromString(String text) {
        return Integer.parseInt(text.replaceAll(REGEX_SELECT_NUMBERS, ""));
    }
}
