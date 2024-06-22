package LearningAndRoughWork;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

public class VerificationAPITest {
	
	
	@Test(enabled=false, priority=1)
	public void verifyUserDetailsFetchingResponse()
	{
		int userId=1;
		String name, username="";
		try {
			Response response=given()
			.baseUri("https://jsonplaceholder.typicode.com")
			.basePath("/users")
			.pathParam("userId", userId)
			.contentType(ContentType.JSON)
			.when()
			.get("/{userId}")
			.then()
			.log().all()
			.statusCode(200)
			.extract()
			.response();
			
			name=response.jsonPath().getString("name");
			username=response.jsonPath().getString("username");
			
			Assert.assertEquals(name, "Leanne Graham", "Name doesn't exist in the response body");
			Assert.assertEquals(username, "Bret", "UserName doesn't exist in the response body");
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	@Test(enabled=false, priority=2)
	public void verifyUserDetailsFetchingUsingHamcrest()
	{
		int userId=1;
		try {
			given()
			.baseUri("https://jsonplaceholder.typicode.com")
			.basePath("/users")
			.pathParam("userId", userId)
			.contentType(ContentType.JSON)
			.when()
			.get("/{userId}")
			.then()
			.log().all()
			.statusCode(200)
			.body("name", equalTo("Leanne Graham")) // Assertion with Hamcrest matche
			.body("username", equalTo("Bret"));
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(enabled=true, priority=3)
	public void verifyUserDetailsWithCustomMessages()
	{
		int userId=1;
		String emailId="";
		try {
			emailId=given()
			.baseUri("https://jsonplaceholder.typicode.com")
			.basePath("/users")
			.pathParam("userId", userId)
			.contentType(ContentType.JSON)
			.when()
			.get("/{userId}")
			.then()
			.log().all()
			.statusCode(200)
			.extract()
			.jsonPath().getString("email");
			
			assertThat(emailId, equalTo("Sincere@april.biz")); // Assertion with Hamcrest matcherAssert
			
		} catch (Exception e) {
			fail("Expected email to be 'Sincere@april.biz' but found: " + emailId);
		}
	}

}
