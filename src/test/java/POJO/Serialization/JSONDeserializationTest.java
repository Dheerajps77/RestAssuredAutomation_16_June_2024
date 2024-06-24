package POJO.Serialization;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

/*
 Deserialization: Converting JSON back to an object.
 */

public class JSONDeserializationTest {
	
	
	@Test
	public void deserializeTest() throws Exception
	{
		POJOPostToDeserialize deserialize = null; 
		int id=3, ids, userID;
		String title, body;
		try {
			deserialize=new POJOPostToDeserialize();
			Response response=given()
			.baseUri("https://jsonplaceholder.typicode.com/")
			.basePath("/posts")
			.pathParam("id", id)
			.contentType(ContentType.JSON)
			.when()
			.get("/{id}")
			.then()
			//.log().all()
			.statusCode(200)
			.extract()
			.response();
			
			System.out.println(response.asPrettyString());
			
			System.out.println("body : "+ response.jsonPath().getString("body"));
			System.out.println("title : " + response.jsonPath().getString("title"));
			
			ObjectMapper mapper=new ObjectMapper();
			deserialize=mapper.readValue(response.asString(), POJOPostToDeserialize.class);
			
			ids=deserialize.getId();
			userID=deserialize.getUserId();
			title=deserialize.getTitle();
			body=deserialize.getBody();
			
			System.out.println("The id ==> " + ids);
			System.out.println("The userId ==> " + userID);
			System.out.println("The title ==> " + title);
			System.out.println("The Body ==> " + body);
			
			
			assertEquals(ids, response.jsonPath().getInt("id"));
			assertEquals(userID, response.jsonPath().getInt("userId"));
			assertEquals(title, response.jsonPath().getString("title"));
			assertEquals(body, response.jsonPath().getString("body"));
			
			
			
		} catch (Exception e) {
			throw e;
		}
	}

}
