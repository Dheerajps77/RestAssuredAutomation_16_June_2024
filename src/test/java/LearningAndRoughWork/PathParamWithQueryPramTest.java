package LearningAndRoughWork;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class PathParamWithQueryPramTest {
	
	
	@Test(enabled=false, priority=0)
	public void validateMoreThanOneQueryParamTest()
	{
		// https://jsonplaceholder.typicode.com/posts?userId=4&title=qui%20est%20esse 
		try {
			
		Response response=given()
			.baseUri("https://jsonplaceholder.typicode.com")
			.basePath("/posts")
			.contentType(ContentType.JSON)
			.queryParam("userId", 4)
			.queryParam("title", "qui explicabo molestiae dolorem")
		.when()
			.get()
		.then()
			.statusCode(200)
			.extract()
			.response();
		
		System.out.println(response.asPrettyString());
		
		
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	@Test(enabled=false, priority=1)
	public void validatePathWithMoreThanOneQueryParamTestWithTestNGAssertion()
	{
		// Normal Assertion using TestNG
		// https://jsonplaceholder.typicode.com/users/1?name=Leanne%20Graham&email=Sincere@april.biz
		String name="Leanne Graham";
		String email="Sincere@april.biz";
		int userId=1;
		try {
			
			Response response=given()
				.baseUri("https://jsonplaceholder.typicode.com")
				.basePath("/users")
				.contentType(ContentType.JSON)
				.pathParam("userId", userId)
				.queryParam("name", name)
				.queryParam("email", email)
			.when()
				.get("/{userId}")
			.then()
				.statusCode(200)
				.extract()
				.response();
			
			assertEquals(name, response.jsonPath().getString("name"));
			assertEquals(email, response.jsonPath().getString("email"));
			assertEquals("Kulas Light", response.jsonPath().getString("address.street"));
			
			System.out.println(response.asPrettyString());
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(enabled=true, priority=1)
	public void validatePathWithMoreThanOneQueryParamTestWithHamCrestAssertion()
	{
		// Normal Assertion using TestNG
		// https://jsonplaceholder.typicode.com/users/1?name=Leanne%20Graham&email=Sincere@april.biz
		String name="Leanne Graham";
		String email="Sincere@april.biz";
		int userId=1;
		try {
			
			given()
				.baseUri("https://jsonplaceholder.typicode.com")
				.basePath("/users")
				.contentType(ContentType.JSON)
				.pathParam("userId", userId)
				.queryParam("name", name)
				.queryParam("email", email)
			.when()
				.get("/{userId}")
			.then()
			.log().all()
				.statusCode(200)
				.body("name", equalTo(name))
				.body("name", containsString("Leanne"))
				.body("email", equalTo("Sincere@april.biz"))
				.body("id", equalTo(1))
				.body("username", notNullValue())
	            .body("address.geo.lat", instanceOf(String.class))
	            .body("address.geo.lat", not(nullValue()))
	            .body("address.geo.lat", greaterThan("-36"))
	            .body("address.geo.lng", lessThan("90"))
	            .time(lessThan(3000L)) // Response time should be less than 2000 milliseconds (2 seconds)
	            .time(greaterThan(500L)) // Response time should be greater than 500 milliseconds
	            .time(allOf(greaterThan(500L), lessThan(7000L))); // Response time should be between 500 milliseconds and 2000 milliseconds
		} catch (Exception e) {
			throw e;
		}
		
/*
1. equalTo(name): Asserts that the name in the response is exactly "Leanne Graham".
2. containsString("Leanne"): Asserts that the name in the response contains "Leanne".
3. notNullValue(): Asserts that the username is not null.
4. instanceOf(String.class): Asserts that the latitude is a String.
5. not(nullValue()): Asserts that the latitude is not null.
6. greaterThan("-90"): Asserts that the latitude is greater than -90.
7. lessThan("90"): Asserts that the longitude is less than 90.
 */
	}

}
