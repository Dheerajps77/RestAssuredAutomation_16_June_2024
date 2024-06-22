package POJO.Serialization;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;


/*
 [
  {
    "id": 1,
    "name": "John Doe",
    "email": "johndoe@example.com"
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "email": "janesmith@example.com"
  }
]
 */

public class JSONArraySerializationTest {
	
	
	@Test
	public void createUsers() throws Exception
	{
		String arrayofUsers;
		try {
			
			User user1=new User();
			user1.setId(43);
			user1.setName("Rohit Singh");
			user1.setEmail("hail@gmail.com");
			
			User user2=new User();
			user2.setId(43);
			user2.setName("Jaju Kumar");
			user2.setEmail("hail@yahoo.com");
			
			User[] users= {user1, user2};
			
			ObjectMapper mapper=new ObjectMapper();
			arrayofUsers=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(users);
			
			given()
			.baseUri("https://jsonplaceholder.typicode.com")
			.basePath("/users")
			.contentType(ContentType.JSON)
			.body(arrayofUsers)
			.when()
			.post()
			.then()
			.log().all()
			.statusCode(201);
			
			
		} catch (Exception e) {
			throw e;
		}
	}

}
