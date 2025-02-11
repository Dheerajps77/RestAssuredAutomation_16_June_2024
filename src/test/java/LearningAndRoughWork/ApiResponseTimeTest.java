import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiResponseTimeTest {

    private static final String BASE_URL = "https://api.example.com";
    private static final String ENDPOINT = "/items";

    @Test
    public void verifyResponseTimeLessThan() {
        given()
            .baseUri(BASE_URL)
            .when()
            .get(ENDPOINT)
            .then()
            .time(lessThan(2000L)); // Response time < 2000ms
    }

    @Test
    public void verifyResponseTimeLessThanOrEqualTo() {
        given()
            .baseUri(BASE_URL)
            .when()
            .get(ENDPOINT)
            .then()
            .time(lessThanOrEqualTo(2000L)); // Response time ≤ 2000ms
    }

    @Test
    public void verifyResponseTimeGreaterThan() {
        given()
            .baseUri(BASE_URL)
            .when()
            .get(ENDPOINT)
            .then()
            .time(greaterThan(500L)); // Response time > 500ms
    }

    @Test
    public void verifyResponseTimeGreaterThanOrEqualTo() {
        given()
            .baseUri(BASE_URL)
            .when()
            .get(ENDPOINT)
            .then()
            .time(greaterThanOrEqualTo(1000L)); // Response time ≥ 1000ms
    }

    @Test
    public void verifyResponseTimeInRange() {
        long responseTime = given()
            .baseUri(BASE_URL)
            .when()
            .get(ENDPOINT)
            .time();

        Assert.assertTrue(responseTime > 500 && responseTime <= 2000,
                "Response time should be between 500ms and 2000ms, but got: " + responseTime);
    }

    @Test
    public void verifyResponseTimeUsingSoftAssertions() {
        SoftAssert softAssert = new SoftAssert();
        Response response = given()
            .baseUri(BASE_URL)
            .when()
            .get(ENDPOINT)
            .then()
            .extract()
            .response();

        long responseTime = response.getTime();
        System.out.println("Response Time: " + responseTime + " ms");

        softAssert.assertTrue(responseTime > 500, "Response time should be greater than 500ms");
        softAssert.assertTrue(responseTime <= 2000, "Response time should be less than or equal to 2000ms");
        softAssert.assertAll(); // Collect all failures and print them together
    }

    @Test
    public void verifyResponseTimeUsingLambda() {
        Response response = given()
            .baseUri(BASE_URL)
            .when()
            .get(ENDPOINT)
            .then()
            .extract()
            .response();

        long responseTime = response.getTime();
        System.out.println("Response Time: " + responseTime + " ms");

        Predicate<Long> withinExpectedTime = time -> time > 500 && time <= 2000;
        Assert.assertTrue(withinExpectedTime.test(responseTime),
                "Response time is not within expected range! Got: " + responseTime);
    }

    @Test
    public void verifyResponseTimeWithRetries() {
        int maxRetries = 3;
        int attempt = 0;
        boolean isResponseTimeValid = false;

        while (attempt < maxRetries) {
            Response response = given()
                .baseUri(BASE_URL)
                .when()
                .get(ENDPOINT)
                .then()
                .extract()
                .response();

            long responseTime = response.getTime();
            System.out.println("Attempt " + (attempt + 1) + " - Response Time: " + responseTime + " ms");

            if (responseTime <= 2000) {
                isResponseTimeValid = true;
                break;
            }
            attempt++;
        }

        Assert.assertTrue(isResponseTimeValid,
                "API response time exceeded 2000ms after " + maxRetries + " retries");
    }

    @Test
    public void verifyResponseTimeInMillisecondsAndSeconds() {
        Response response = given()
            .baseUri(BASE_URL)
            .when()
            .get(ENDPOINT)
            .then()
            .extract()
            .response();

        long timeInMillis = response.getTime(); // Time in milliseconds
        long timeInSeconds = response.getTimeIn(TimeUnit.SECONDS); // Time in seconds

        System.out.println("Response Time in ms: " + timeInMillis);
        System.out.println("Response Time in sec: " + timeInSeconds);

        Assert.assertTrue(timeInMillis < 2000, "Response time should be less than 2000ms");
        Assert.assertTrue(timeInSeconds < 2, "Response time should be less than 2 seconds");
    }

    @Test
    public void verifyResponseTimeWithAllMatchers() {
        long responseTime = given()
            .baseUri(BASE_URL)
            .when()
            .get(ENDPOINT)
            .time();

        Assert.assertThat("Response time validation failed!",
                responseTime,
                allOf(greaterThan(500L), lessThanOrEqualTo(2000L)));
    }
}
