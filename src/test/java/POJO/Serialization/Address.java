package POJO.Serialization;

/*
 {
  "id": 1,
  "name": "John Doe",
  "email": "johndoe@example.com",
  "address": {
    "street": "123 Main St",
    "city": "New York",
    "zipcode": "10001"
  }
}

 */

public class Address {
	
	private String street;
	private String city;
	private String zipCode;
	
	public static final int n=10;
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
}
