package com.petstore.api.POJO.payloads;

import java.util.List;

public class PetDetails {

	int id;
	PetCategory category;
	String name;
	List<String> photoURLs;
	List<PetTags> petTags;
	String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public PetCategory getCategory() {
		return category;
	}

	public void setCategory(PetCategory category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getPhotoURLs() {
		return photoURLs;
	}

	public void setPhotoURLs(List<String> photoURLs) {
		this.photoURLs = photoURLs;
	}

	public List<PetTags> getPetTags() {
		return petTags;
	}

	public void setPetTags(List<PetTags> petTags) {
		this.petTags = petTags;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
