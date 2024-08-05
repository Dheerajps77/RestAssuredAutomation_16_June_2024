package POJO.DeSerialization;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class CompareJSON {
	
	
	private String BASE_URI="https://run.mocky.io";
	private String BASE_PATH="/v3";
	private String id="50c46227-42bd-4ce6-816a-36aca839fb51";
	private String user_id="5c18cbbf-a93b-41e6-91ee-79c6e55dd72b";
	
	
	// Below are HardAssertion - hard assertions mean that if one assertion fails, 
	//the execution stops, and subsequent assertions are not evaluated
	@Test(enabled=false)
	public void compareJSONBodyWithVerifyUsingHardAssertion()
	{
		try {
			
		given()
			.baseUri(BASE_URI)
			.basePath(BASE_PATH)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParam("id", id)
			.when()
			.get("/{id}")
			.then()
			.log()
			.all()
			.statusCode(200)
			.assertThat()
			.body(matchesJsonSchemaInClasspath("schemas/userSchema.json"))
			.and()
			.body("status", equalTo("success"))
			.body("data.user.address.street", equalTo("123 Main St"))
			.body("data.user.phoneNumbers[1].type", equalTo("work"))
			.body("data.user.orders.items[1].itemId", notNullValue());
			
	
		} catch (Exception e) {
			throw e;
		}
	}
	
	// here all assertions are evaluated even if some of them fail, 
	//you would need to use a different approach, as Rest Assured does
	//not natively support soft assertions
		@Test(enabled=false)
		public void compareJSONBodyWithVerifyUsingSoftAssertion()
		{
			try {
				
			Response response=given()
				.baseUri(BASE_URI)
				.basePath(BASE_PATH)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("id", id)
				.when()
				.get("/{id}");
				/*
				.then()
				.log()
				.all()
				.statusCode(200)
				.assertThat()*/
				
			
			response.then().log().all().assertThat().body(matchesJsonSchemaInClasspath("schemas/userSchema.json"));
			
			SoftAssert assertion=new SoftAssert();
			String status=response.jsonPath().getString("status");			
			assertion.assertEquals(status, "success");
			
			assertion.assertEquals(response.jsonPath().get("status"), "success");
			assertion.assertEquals(response.jsonPath().get("data.user.address.street"), "123 Main St");
			assertion.assertAll();
			
			} catch (Exception e) {
				throw e;
			}
		}
		
		
		@Test(enabled=true, dependsOnMethods = {"userNamesPostCall"})
		public void userNamesGetCall()
		{
			try {
				Response response= given()
				.baseUri(BASE_URI)
				.basePath(BASE_PATH)
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.pathParam("userId", user_id)
				.when()
				.get("/{userId}")
				.then()
				.log().all()
				.assertThat()
				.statusCode(200)
				.extract()
				.response();
				
				//System.out.println(response.asString());
			} catch (Exception e) {
				throw e;
			}
		}
		
		@Test(enabled=true)
		public void userNamesPostCall()
		{
			try {
				given()
				.baseUri(BASE_URI)
				.basePath("/v3/99d63e98-9433-494c-8271-a8207bb8a3d5")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				//.pathParam("userId", "a1fdfebd-7c7e-4c75-a8a1-8f86bde67498")
				.body("{ \"name\": \"Kulta kutta\", \"status\": \"Inactive\" }")
				.when()
				.post()
				.then()
				.log().all()
				.assertThat()
				.statusCode(200)
				.body("name", equalTo("Kulta Singh"));
			} catch (Exception e) {
				throw e;
			}
		}

}
