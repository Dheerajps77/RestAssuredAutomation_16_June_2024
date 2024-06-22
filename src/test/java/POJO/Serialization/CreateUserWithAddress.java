package POJO.Serialization;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class CreateUserWithAddress {

	
	@Test
	public void createUserWithAddress() throws Exception
	{
		User user;
		Address address;
		String userData;
		try {
			
			address=new Address();
			address.setCity("New Delhi");
			address.setStreet("Ratiya marg");
			address.setZipCode("343433");
			
			user=new User();
			user.setId(21);
			user.setName("Dheeraj Singh");
			user.setEmail("Dheeraj@gmail.com");
			user.setAddress(address);
			
			ObjectMapper mapper=new ObjectMapper();
			userData=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
			
			given()
			.baseUri("https://jsonplaceholder.typicode.com")
			.basePath("/users")
			.contentType(ContentType.JSON)
			.body(userData)
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
