package com.petstore.api.tests;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class storeTest {
	
	
	@Test
	public void storeInventory()
	{
		Response response;
		try {
		response =  given()
				.baseUri("https://petstore.swagger.io")
				.basePath("/v2/store")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.get("/inventory");
			
			System.out.println(response.body().asPrettyString());
			
		} catch (Exception e) {
			throw e;
		}
	}

}
