package LearningAndRoughWork;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.jsonschema.JsonSchema;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class UserSchemaValidationTest {
	
	
	@BeforeClass
	public void setUp()
	{
		try {
			RestAssured.baseURI="https://jsonplaceholder.typicode.com";
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test
	public void validateUserJsonSchema()
	{
		try {
			
			given()
			.contentType(ContentType.JSON)
			.basePath("/users")
			.pathParam("userId", 1)
			.when()
			.get("/{userId}")
			.then()
			.statusCode(200)
			.assertThat()
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/user-schema.json"));
			
		} catch (Exception e) {
			throw e;
		}
	}
}
