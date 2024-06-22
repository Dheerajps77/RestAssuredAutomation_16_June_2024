package LearningAndRoughWork;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class HamcrestValidationTest {
	
	 @BeforeClass
	    public void setUp() {
	        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
	    }

	    @Test
	    public void validateUserDetails() {
	    	
	    	try {
	    		int id=1;
		        given()
		        .basePath("/users")
		        .pathParam("userId", id)
		        .contentType(ContentType.JSON)
		            .when()
		            .get("/{userId}")
		            .then()
		            .log().all()
		            .statusCode(200)
		            .body("name", equalTo("Leanne Graham"))
		            .body("username", equalTo("Bret"))
		            .body("email", containsString("Sincere@april.biz"))
		            .body("address.city", equalTo("Gwenborough"))
		            .body("phone", equalTo("1-770-736-8031 x56442"))
		            .body("website", equalTo("hildegard.org"))
		            .body("company.name", equalTo("Romaguera-Crona"))
		            .body("company.catchPhrase", equalTo("Multi-layered client-server neural-net"))
		            .body("age", greaterThan(18))
		            .body("email", containsString("Sincere@april.biz"))
		            .body("roles", hasItems("admin", "user"))
		            .body("active", is(true), "User should be active")
		            .body("address.find { it.city == 'Gwenborough' }.zipcode", equalTo("92998-3874"))
		            .body("address.find { it.suite == 'Apt. 556' }.suite", equalTo("Apt. 556"));
			} catch (Exception e) {
				throw e;
			}
	    }
}
