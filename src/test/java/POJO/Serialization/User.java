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

public class User {
	private int id;
	private String name;
	private String email;
	private Address address;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
}
