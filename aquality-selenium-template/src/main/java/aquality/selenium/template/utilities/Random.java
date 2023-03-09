package aquality.selenium.template.utilities;

import com.github.javafaker.Faker;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Random {
    private static final int MIN = 10;
    private static final int MAX = 15;
    private static final Faker faker = new Faker();
    private static final java.util.Random random = new java.util.Random();

    public static String getRandomText(int min, int max) {
        return faker.internet().password(min, max);
    }

    public static String getRandomText() {
        return getRandomText(MIN, MAX);
    }

    public static int getRandomIntNumber(int max) {
        return random.nextInt(max);
    }
}
