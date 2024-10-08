package com.petstore.api.endpoints;

public class Routes {
	public static String base_url = "https://petstore.swagger.io/v2";

	// User module

	public static String post_url = base_url + "/user";
	public static String get_url = base_url + "/user/{username}";
	public static String update_url = base_url + "/user/{username}";
	public static String delete_url = base_url + "/user/{username}";

	// Store module
	
	public static String base_URI_Inventory="https://petstore.swagger.io";
	public static String base_PATH_Inventory="/v2/store";
	public static String base_PATH_PET="/v2/pet";
	// Here you will create Store module URL's

	// Pet module
	// Here you will create Pet module URL's

}