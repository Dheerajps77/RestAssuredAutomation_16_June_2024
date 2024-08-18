package com.petstore.api.POJO.payloads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

public class PetPOSTData {
	private Faker faker;
	private Random random;
	private PetCategory category;
	private PetTags petTags;

	public PetPOSTData() {
		this.faker = new Faker();
		this.category = new PetCategory();
		this.petTags = new PetTags();
		this.random = new Random();
	}

	public List<PetDetails> generatePetData(int count) {
		List<PetDetails> details = new ArrayList<PetDetails>();
		try {
			for (int i = 0; i < count; i++) {
				details.add(createPetDetails());
			}

		} catch (Exception e) {
			throw e;
		}
		return details;
	}

	public PetDetails createPetDetails() {
		PetDetails petDetails;
		try {
			petDetails = new PetDetails();
			petDetails.setId(faker.idNumber().hashCode());

			// Set Category Class Value
			category.setCatId(faker.idNumber().hashCode());
			category.setCatName(faker.animal().name());
			petDetails.setCategory(category);

			petDetails.setName(faker.animal().name());
			petDetails.setPhotoURLs(List.of(faker.internet().image()));

			// Set PetTag Class Value
			petTags.setTagId(faker.idNumber().hashCode());
			petTags.setTagName(faker.name().fullName());
			petDetails.setPetTags(List.of(petTags));

			String status[] = { "available", "pending", "sold", "unavailable", "adopted" };
			
			int num=random.nextInt(status.length);
			petDetails.setStatus(status[num]);
		} catch (Exception e) {
			throw e;
		}
		return petDetails;
	}

	public String createPet(PetDetails petDetails) throws Exception {
		ObjectMapper mapper;
		String value;
		try {
			mapper = new ObjectMapper();
			value = mapper.writeValueAsString(petDetails);

		} catch (Exception e) {
			throw e;
		}
		return value;
	}

	public String createRandomPet(PetDetails petDetails) throws Exception {
		ObjectMapper mapper;
		String value;
		try {
			mapper = new ObjectMapper();
			value = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(petDetails);

		} catch (Exception e) {
			throw e;
		}
		return value;
	}
}
