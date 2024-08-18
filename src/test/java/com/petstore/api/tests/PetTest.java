package com.petstore.api.tests;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.petstore.api.POJO.payloads.PetDetails;
import com.petstore.api.POJO.payloads.PetPOSTData;
import com.petstore.api.endpoints.Routes;

import io.restassured.http.ContentType;
public class PetTest {
	
	PetPOSTData postDetails=new PetPOSTData();
	@DataProvider(name ="petData")
	public Object[][] petDataProvider()
	{
		Object[][] petData;
		try {
			List<PetDetails> pets= postDetails.generatePetData(2);
			petData= new Object[pets.size()][1];
			
			for (int i = 0; i < pets.size(); i++) {
	            petData[i][0] = pets.get(i);
	        }
			
		} catch (Exception e) {
				throw e;
		}
		return petData;
	}
	
	@Test(enabled=true, dataProvider = "petData")
	public void petDataTestWithDataProvider(PetDetails petData) throws Exception
	{
		try {
			given()
			.baseUri(Routes.base_URI_Inventory)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.basePath(Routes.base_PATH_PET)
			.log().all()
			.when()
			.body(postDetails.createRandomPet(petData))
			.post()
			.then()
			.log().all()
			.assertThat()
			.statusCode(200);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Test(enabled=false)
	public void petDataTest() throws Exception
	{
		String finalURL=Routes.base_URI_Inventory+Routes.base_PATH_PET;
		System.out.println(finalURL);
		PetDetails petDetails=postDetails.createPetDetails();
		String jsonBody=postDetails.createPet(petDetails);
		System.out.println(jsonBody);
		try {
			given()
			.baseUri(Routes.base_URI_Inventory)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.basePath(Routes.base_PATH_PET)
			.log().all()
			.when()
			.body(postDetails.createPet(petDetails))
			.post()
			.then()
			.log().all()
			.assertThat()
			.statusCode(200);
			
		} catch (Exception e) {
			throw e;
		}
	}

}
