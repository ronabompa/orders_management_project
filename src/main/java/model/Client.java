package model;

import java.util.Arrays;

/**
 * Clasa care contine datele Client cu care se vor lucra
 */
public class Client {

	private int id;
	private String name;
	private String address;
	private String email;
	private int age;

	// CONSTRUCTORI

	// SETTERS & GETTERS
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	// !!!!!!!!!TO USE EMAIL VALIDATOR!!!!!!!!!
	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/**
	 *  Method that converts Clients attributes to Object array
	 * @return an array of Objects
	 */
	public Object[] toArray()
	{
		Object[] clientDetailsArray = new Object[5];

		clientDetailsArray[0] = this.getId();
		clientDetailsArray[1] = this.getName();
		clientDetailsArray[2] = this.getAddress();
		clientDetailsArray[3] = this.getEmail();
		clientDetailsArray[4] = this.getAge();

		return clientDetailsArray;

	}


}
