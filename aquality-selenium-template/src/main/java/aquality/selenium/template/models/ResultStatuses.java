package aquality.selenium.template.models;

import aquality.selenium.template.utilities.Random;
import java.util.Arrays;
import java.util.List;

public enum ResultStatuses {
    PASSED,
    FAILED,
    SKIPPED;

    public static String getRandomStatus() {
        List<ResultStatuses> values = Arrays.asList(values());
        return values.get(Random.getRandomIntNumber(0, values().length)).toString();
    }
}
