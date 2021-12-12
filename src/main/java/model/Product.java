package model;

/**
 * Clasa care contine datele Client cu care se vor lucra
 */
public class Product {

	private int id;
	private String name;
	private float price;
	private int quantityStock;
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

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantityStock() {
		return quantityStock;
	}

	public void setQuantityStock(int quantityStock) {
		this.quantityStock = quantityStock;
	}

	public Object[] toArray()
	{
		Object[] productDetailsArray = new Object[4];

		productDetailsArray[0] = this.getId();
		productDetailsArray[1] = this.getName();
		productDetailsArray[2] = this.getPrice();
		productDetailsArray[3] = this.getQuantityStock();

		return productDetailsArray;

	}



}
