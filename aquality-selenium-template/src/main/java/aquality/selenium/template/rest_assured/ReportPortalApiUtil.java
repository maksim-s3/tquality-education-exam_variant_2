package aquality.selenium.template.rest_assured;

import java.util.List;
import static aquality.selenium.template.rest_assured.RestClient.getBaseSpec;
import static io.restassured.RestAssured.given;
import aquality.selenium.template.models.Test;
import aquality.selenium.template.models.UploadAttachment;
import io.restassured.http.ContentType;

public class ReportPortalApiUtil {
    private static final String TOKEN_GENERATE = "token/get";
    private static final String GET_ALL_TESTS_PROJECT_JSON = "test/get/json";
    private static final String PUT_TEST = "test/put";
    private static final String UPDATE_TEST = "test/update";
    private static final String PUT_LOG = "test/put/log";
    private static final String PUT_ATTACHMENT = "test/put/attachment";
    public static String getToken(int numberVariant) {
        return given()
                    .spec(getBaseSpec())
                    .queryParam(RequestParams.VARIANT.toString(), numberVariant)
                    .post(TOKEN_GENERATE)
                .then()
                    .extract().response().body().asString();
    }

    public static List<Test> getAllTestsFromProject(int projectId) {
        return given()
                    .spec(getBaseSpec())
                    .queryParam(RequestParams.PROJECT_ID.toString(), projectId)
                    .post(GET_ALL_TESTS_PROJECT_JSON)
                .then()
                    .extract().body().jsonPath().getList("", Test.class);
    }

    public static int putTest(Test test) {
        return Integer.parseInt(given()
                    .spec(getBaseSpec())
                    .queryParam(RequestParams.SID.toString(), test.getSid())
                    .queryParam(RequestParams.PROJECT_NAME.toString(), test.getProjectName())
                    .queryParam(RequestParams.TEST_NAME.toString(), test.getName())
                    .queryParam(RequestParams.METHOD_NAME.toString(), test.getMethod())
                    .queryParam(RequestParams.ENV.toString(), test.getEnv())
                    .queryParam(RequestParams.BROWSER.toString(), test.getBrowser())
                    .post(PUT_TEST)
                .then()
                    .extract().response().asString());
    }

    public static void updateTest(Test test) {
        given()
                    .spec(getBaseSpec())
                    .queryParam(RequestParams.TEST_ID.toString(), test.getId())
                    .queryParam(RequestParams.STATUS.toString(), test.getStatus())
                    .post(UPDATE_TEST);
    }

    public static void putLog(Test test, String log) {
        given()
                    .spec(getBaseSpec())
                    .queryParam(RequestParams.TEST_ID.toString(), test.getId())
                    .queryParam(RequestParams.CONTENT.toString(), log)
                    .post(PUT_LOG);
    }

    public static void putAttachment(Test test, UploadAttachment attachment) {
        given()
                    .spec(getBaseSpec())
                    .contentType(ContentType.JSON)
                    .queryParam(RequestParams.TEST_ID.toString(), test.getId())
                    .body(attachment)
                    .post(PUT_ATTACHMENT);
    }
}
