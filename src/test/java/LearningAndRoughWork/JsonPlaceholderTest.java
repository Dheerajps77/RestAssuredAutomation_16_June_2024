package LearningAndRoughWork;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class JsonPlaceholderTest {
@Test
public void testFilterUserByName() {
    // Set the base URI for JSONPlaceholder API
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

    // Make a GET request to fetch all users
    Response response = given()
        .contentType(ContentType.JSON)
        .when()
        .get("/users")
        .then()
        .statusCode(200) // Expecting HTTP 200 OK status
        .extract()
        .response();

    // Scenario 1: Filter by name
    String nameToFilter = "Clementine Bauch";
    Integer idByName = response.jsonPath().getInt("find { it.name == '" + nameToFilter + "' }.id");
    String usernameByName = response.jsonPath().getString("find { it.name == '" + nameToFilter + "' }.username");
    String emailByName = response.jsonPath().getString("find { it.name == '" + nameToFilter + "' }.email");

    assertEquals(3, idByName);
    assertEquals("Samantha", usernameByName);
    assertEquals("Nathan@yesenia.net", emailByName);
    
    System.out.println("Extraction basis on Name");
    System.out.println("Id basis on Name " + idByName);
    System.out.println("name basis on Name " + usernameByName);
    System.out.println("Email basis on Name " + emailByName);

    // Scenario 2: Filter by username
    String usernameToFilter = "Bret";
    Integer idByUsername = response.jsonPath().getInt("find { it.username == '" + usernameToFilter + "' }.id");
    String nameByUsername = response.jsonPath().getString("find { it.username == '" + usernameToFilter + "' }.name");
    String emailByUsername = response.jsonPath().getString("find { it.username == '" + usernameToFilter + "' }.email");
    

    assertEquals(1, idByUsername);
    assertEquals("Leanne Graham", nameByUsername);
    assertEquals("Sincere@april.biz", emailByUsername);
    
    
    System.out.println("Extraction basis on UserName");
    System.out.println("Id basis on userName " + idByUsername);
    System.out.println("name basis on userName " + nameByUsername);
    System.out.println("Email basis on userName " + emailByUsername);
    
    // Scenario 3: Filter by email
    String emailToFilter = "Shanna@melissa.tv";
    Integer idByEmail = response.jsonPath().getInt("find { it.email == '" + emailToFilter + "' }.id");
    String nameByEmail = response.jsonPath().getString("find { it.email == '" + emailToFilter + "' }.name");
    String usernameByEmail = response.jsonPath().getString("find { it.email == '" + emailToFilter + "' }.username");

    assertEquals(2, idByEmail);
    assertEquals("Ervin Howell", nameByEmail);
    assertEquals("Antonette", usernameByEmail);
    
    System.out.println("Extraction basis on Email");
    System.out.println("Id basis on Email " + idByEmail);
    System.out.println("name basis on Email " + usernameByEmail);
    System.out.println("Email basis on Email " + usernameByEmail);

    // Scenario 4: Filter by phone
    String phoneToFilter = "1-463-123-4447";
    Integer idByPhone = response.jsonPath().getInt("find { it.phone == '" + phoneToFilter + "' }.id");
    String nameByPhone = response.jsonPath().getString("find { it.phone == '" + phoneToFilter + "' }.name");
    String usernameByPhone = response.jsonPath().getString("find { it.phone == '" + phoneToFilter + "' }.username");

    assertEquals(3, idByPhone);
    assertEquals("Clementine Bauch", nameByPhone);
    assertEquals("Samantha", usernameByPhone);

    // Scenario 5: Filter by city
    String cityToFilter = "Gwenborough";
    Integer idByCity = response.jsonPath().getInt("find { it.address.city == '" + cityToFilter + "' }.id");
    String nameByCity = response.jsonPath().getString("find { it.address.city == '" + cityToFilter + "' }.name");
    String usernameByCity = response.jsonPath().getString("find { it.address.city == '" + cityToFilter + "' }.username");

    assertEquals(1, idByCity);
    assertEquals("Leanne Graham", nameByCity);
    assertEquals("Bret", usernameByCity);

    // Scenario 6: Filter by company name
    String companyNameToFilter = "Romaguera-Crona";
    Integer idByCompanyName = response.jsonPath().getInt("find { it.company.name == '" + companyNameToFilter + "' }.id");
    String nameByCompanyName = response.jsonPath().getString("find { it.company.name == '" + companyNameToFilter + "' }.name");
    String usernameByCompanyName = response.jsonPath().getString("find { it.company.name == '" + companyNameToFilter + "' }.username");
    
    assertEquals(1, idByCompanyName);
    assertEquals("Leanne Graham", nameByCompanyName);
    assertEquals("Bret", usernameByCompanyName);
}
}
