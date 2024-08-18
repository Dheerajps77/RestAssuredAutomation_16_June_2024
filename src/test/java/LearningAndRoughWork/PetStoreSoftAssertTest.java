package LearningAndRoughWork;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;

public class PetStoreSoftAssertTest {

	 @Test
	    public void testPetDetailsWithSoftAssertions() {
	        Response response = RestAssured.given()
	            .baseUri("https://petstore.swagger.io/v2")
	            .contentType(ContentType.JSON)
	            .when()
	            .log().all()
	            .get("/pet/1"); // Replace with the actual pet ID you want to test
	        	
	        
	        Headers headers= response.getHeaders();
	        
	        for(Header header : headers)
	        {
	        	System.out.println(header.getName() + " : " + header.getValue());
	        }
	        
	        SoftAssert softAssert = new SoftAssert();
	        
	        // Hard assertion for status code
	        softAssert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");

			// Soft assertions for body content
			softAssert.assertEquals(response.jsonPath().getInt("id"), 1, "ID mismatch");
			softAssert.assertEquals(response.jsonPath().getString("name"), "doggie", "Name mismatch");
			softAssert.assertEquals(response.jsonPath().getString("status"), "available", "Status mismatch");

			// Assert all soft assertions
			softAssert.assertAll();
		}
	}
