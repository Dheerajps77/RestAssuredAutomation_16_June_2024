package POJO.DeSerialization;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	
	private int userid;
	private String name;
	private String email;
	private Address address;
	private List<PhoneNumbers> phoneNumbers;
	private List<Order> orders;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
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
	public List<PhoneNumbers> getPhoneNumbers() {
		return phoneNumbers;
	}
	public void setPhoneNumbers(List<PhoneNumbers> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	
	

	/*
	 {
	  "status": "success",
	  "data": {
	    "user": {
	      "id": 1,
	      "name": "John Doe",
	      "email": "john.doe@example.com",
	      "address": {
	        "street": "123 Main St",
	        "city": "Anytown",
	        "zipcode": "12345"
	      },
	      "phoneNumbers": [
	        {"type": "home", "number": "555-555-5555"},
	        {"type": "work", "number": "555-555-5556"}
	      ],
	      "orders": [
	        {
	          "orderId": 101,
	          "items": [
	            {"itemId": 1, "itemName": "Item A", "quantity": 2},
	            {"itemId": 2, "itemName": "Item B", "quantity": 1}
	          ]
	        },
	        {
	          "orderId": 102,
	          "items": [
	            {"itemId": 3, "itemName": "Item C", "quantity": 5}
	          ]
	        }
	      ]
	    }
	  }
	}

	 */
}
