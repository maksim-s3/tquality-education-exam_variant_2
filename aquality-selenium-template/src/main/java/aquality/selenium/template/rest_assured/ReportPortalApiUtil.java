package aquality.selenium.template.rest_assured;

import java.util.List;
import static aquality.selenium.template.rest_assured.RequestParams.*;
import static aquality.selenium.template.rest_assured.RestClient.getBaseSpec;
import static io.restassured.RestAssured.given;
import aquality.selenium.template.models.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.exception.JsonPathException;

import static org.apache.http.HttpStatus.*;

public class ReportPortalApiUtil {
    private static final String TOKEN_GENERATE = "token/get";
    private static final String GET_ALL_TESTS_FROM_PROJECT_JSON = "test/get/json";
    private static final String PUT_TEST = "test/put";
    private static final String PUT_LOG = "test/put/log";
    private static final String PUT_ATTACHMENT = "test/put/attachment";
    private static final String UPDATE_TEST = "test/update";

    public static String getToken(int numberVariant) {
        return given()
                    .spec(getBaseSpec())
                    .queryParam(VARIANT.toString(), numberVariant)
                    .post(TOKEN_GENERATE)
                .then()
                    .statusCode(SC_OK)
                    .extract().response().body().asString();
    }

    public static List<Test> getAllTestsFromProject(int projectId) {
        try {
            return given()
                        .spec(getBaseSpec())
                        .queryParam(PROJECT_ID.toString(), projectId)
                        .post(GET_ALL_TESTS_FROM_PROJECT_JSON)
                    .then()
                        .extract().body().jsonPath().getList("", Test.class);
        } catch (JsonPathException exception) {
            throw new JsonPathException("The response is not a json document", exception);
        }

    }

    public static int putTest(Test test) {
        return Integer.parseInt(given()
                    .spec(getBaseSpec())
                    .queryParam(SID.toString(), test.getSid())
                    .queryParam(PROJECT_NAME.toString(), test.getProjectName())
                    .queryParam(TEST_NAME.toString(), test.getName())
                    .queryParam(METHOD_NAME.toString(), test.getMethod())
                    .queryParam(ENV.toString(), test.getEnv())
                    .queryParam(BROWSER.toString(), test.getBrowser())
                    .post(PUT_TEST)
                .then()
                    .extract().response().asString());
    }

    public static void updateTest(Test test) {
        given()
                    .spec(getBaseSpec())
                    .queryParam(TEST_ID.toString(), test.getId())
                    .queryParam(STATUS.toString(), test.getStatus())
                    .post(UPDATE_TEST);
    }

    public static void putLog(Test test, String log) {
        given()
                    .spec(getBaseSpec())
                    .queryParam(TEST_ID.toString(), test.getId())
                    .queryParam(CONTENT.toString(), log)
                    .post(PUT_LOG);
    }

    public static void putAttachment(Test test, UploadAttachment attachment) {
        given()
                    .spec(getBaseSpec())
                    .contentType(ContentType.JSON)
                    .queryParam(TEST_ID.toString(), test.getId())
                    .body(attachment)
                    .post(PUT_ATTACHMENT);
    }
}
