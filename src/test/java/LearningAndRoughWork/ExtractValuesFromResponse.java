package LearningAndRoughWork;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ExtractValuesFromResponse {
	int userIdwW=1;
	
	
	@Test(enabled=false, priority=2)
	public void getResponseOntheBasisonID()
	{
		int id=2;
		try {
			
			Response response=given()
			.baseUri("https://jsonplaceholder.typicode.com")
			.basePath("/users")
			.pathParam("id", id)
			.contentType(ContentType.JSON)
			.when()
			.get("/{id}")
			.then()
			.log().all()
			.extract()
			.response();
			
			System.out.println(response.asPrettyString());
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(enabled=false, priority=0)
	public void testExtractSingleValueFromResponse()
	{
		int userIdwW=1;
		try {
			Response response= given()
			.baseUri("https://jsonplaceholder.typicode.com")
			.basePath("/users")
			.pathParam("userId", userIdwW)
			.contentType(ContentType.JSON)
			.when()
			.get("/{userId}")
			.then()
			.statusCode(200)
			.extract()
			.response();
			
			int id = response.path("id");
			String name = response.path("name");
			String username = response.path("username");
			String email = response.path("email");
			
			
			assertEquals(1, id);
	        assertEquals("Leanne Graham", name);
	        assertEquals("Bret", username);
	        assertEquals("Sincere@april.biz", email);

	        // Print individual values for verification
	        System.out.println("User ID: " + id);
	        System.out.println("Name: " + name);
	        System.out.println("Username: " + username);
	        System.out.println("Email: " + email);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(enabled=false, priority=1)
	public void testExtractSingleValueUsingExtractMethodWithPathAndQueryParameters()
	{
		try {
			
			Response response=given()
			.baseUri("https://jsonplaceholder.typicode.com")
			.basePath("/users")
			.pathParam("userId", userIdwW)
			//.queryParam("status", status)
			.contentType(ContentType.JSON)
			.when()
			.get("/users/{userId}")
			.then()
			.log().all()
			.statusCode(200)
			.extract()
			.response();
			
			// Extract values using the path method
	        int id = response.path("id");
	        String name = response.path("name");
	        String username = response.path("username");
	        String email = response.path("email");

	        // Assert the extracted values
	        assertEquals(1, id);
	        assertEquals("Leanne Graham", name);
	        assertEquals("Bret", username);
	        assertEquals("Sincere@april.biz", email);

	        // Print the extracted values
	        System.out.println("ID: " + id);
	        System.out.println("Name: " + name);
	        System.out.println("Username: " + username);
	        System.out.println("Email: " + email);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(enabled=true, priority=3)
	public void testFilterUserByName()
	{
		String nameToFilter="Clementine Bauch";
		try {
			
			Response response=given()
			.baseUri("https://jsonplaceholder.typicode.com")
			.basePath("/users")
			.contentType(ContentType.JSON)
			.when()
			.get()
			.then()
			//.log().all()
			.statusCode(200)
			.extract()
			.response();
			
			//System.out.println(response.asPrettyString());
			
			String str=response.jsonPath().getString("find { it.name == '"+nameToFilter+"' }");
			System.out.println(str);
			
			 // Extract and verify details of the user with the specified name
	        Integer id = response.jsonPath().getInt("find { it.name == '" + nameToFilter + "' }.id");
	        String username = response.jsonPath().getString("find { it.name == '" + nameToFilter + "' }.username");
	        String email = response.jsonPath().getString("find { it.name == '" + nameToFilter + "' }.email");
	        
	     // Validate the user details
	        assertEquals(3, id);
	        assertEquals("Samantha", username);
	        assertEquals("Nathan@yesenia.net", email);

	        // Print individual values for verification
	        System.out.println("User ID: " + id);
	        System.out.println("Username: " + username);
	        System.out.println("Email: " + email);
			
		} catch (Exception e) {
			throw e;
		}
	}
}
