package com.petstore.api.assertions;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Assertions {

	public static String base_URI_Inventory;
    public static String base_PATH_Inventory;
    
 // Helper method to assert JSON value equality
    private void assertJsonValueEquals(JsonPath jsonPath, String jsonKey, int expectedValue, String fieldName) {
        Integer actualValue = jsonPath.getInt(jsonKey);
        Assert.assertEquals(actualValue, expectedValue, fieldName + " mismatch! Expected: " + expectedValue + ", Actual: " + actualValue);
    }
    
	@BeforeMethod
	public void initialisationSetup() {
		try {
			base_URI_Inventory = "https://petstore.swagger.io";
			base_PATH_Inventory = "/v2/store";
			
		} catch (Exception e) {
			throw e;
		}
	}

	@Test(enabled=true)
	public void testInventoryResponseHardAssertions() {
		try {
		Response response=given()
			.baseUri(base_URI_Inventory)
			.basePath(base_PATH_Inventory+ "/inventory")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.get()
			.then()
			.log().all()
			.extract()
			.response();
		
		JsonPath jsonPath = response.jsonPath();
		Assert.assertEquals(jsonPath.getInt("sold"), 9);
		Assert.assertEquals(jsonPath.getInt("Sold"), 1);
		Assert.assertEquals(jsonPath.getInt("6000"), 1);
		
		
		 // Assert specific values from JSON response
        assertJsonValueEquals(jsonPath, "sold", 10, "Sold count");
        assertJsonValueEquals(jsonPath, "pending", 2, "Pending count");
        assertJsonValueEquals(jsonPath, "available", 223, "Available count");

        // Print response details
        System.out.println("Response Body:");
        System.out.println(response.getBody().asString());

        System.out.println("Response Session:");
        System.out.println(response.getSessionId());
        
        System.out.println("Response StatusCode:");
        System.out.println(response.getStatusCode());
        
        System.out.println("Response StatusLine:");
        System.out.println(response.getStatusLine());
        
        System.out.println("Response time:");
        System.out.println(response.getTime());
	} catch (Exception e) {
			throw e;
		}
	}
	

	@Test(enabled=false)
	public void testInventoryResponseSoftAssertions() {
		try {
			
			 // Make API call
	        Response response = given()
	            .baseUri(base_URI_Inventory)
	            .basePath(base_PATH_Inventory + "/inventory")
	            .when()
	            .get()
	            .then()
	            .extract()
	            .response();

	        // Soft Assertions
	        SoftAssert softAssert = new SoftAssert();

	        // Verify status code
	        softAssert.assertEquals(response.statusCode(), 200, "Status code mismatch!");

	        // Parse response body using JsonPath
	        JsonPath jsonPath = response.jsonPath();
	        // Print response details
	        System.out.println("Response Body:");
	        System.out.println(response.getBody().asString());

	        // Assert all and collect failures
	        softAssert.assertAll();
	    }
		catch (Exception e) {
			throw e;
		}
	}
}
