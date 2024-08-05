package POJO.DeSerialization;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ComplexUserTestDeserialize {
	
	
	private String BASE_URI="https://run.mocky.io";
	private String BASE_PATH="/v3";
	private String id="50c46227-42bd-4ce6-816a-36aca839fb51";
	ResponseData responseData;
	
	@Test
	public void deSerializeData() throws Exception
	{
		try {
			
			Response response=given()
			.baseUri(BASE_URI)
			.basePath(BASE_PATH)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.pathParams("id", id)
			.when()
			.get("/{id}")
			.then()
			.assertThat()
			.statusCode(200)
			.extract().response();
			
			String jsonPath= response.asPrettyString();
			System.out.println(jsonPath);
			
			ObjectMapper mapper=new ObjectMapper();
			responseData=mapper.readValue(jsonPath, ResponseData.class);
			
			System.out.println("===================================================");
			
			System.out.println("Status -> "+ responseData.getStatus());
			System.out.println("Email -> "+ responseData.getData().getUser().getEmail());
			System.out.println("Name -> "+ responseData.getData().getUser().getName());
			
			for(PhoneNumbers numbers2 : responseData.getData().getUser().getPhoneNumbers())
			{
				System.out.println("Phone number -> "+ numbers2.getNumber());
				System.out.println("Phone Type -> "+ numbers2.getType());
			}
			
			for(Order order:responseData.getData().getUser().getOrders())
			{
				List<Items> items=order.getItems();
				
				for(Items items1 : items)
				{
					System.out.println("items Id : " + items1.getItemId());
					System.out.println("items Name : " + items1.getItemName());
					System.out.println("items Qantity : " + items1.getQantity());
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
