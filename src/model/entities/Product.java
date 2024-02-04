package model.entities;

import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L; 
    private int id;
    private String name;
	private String description;
    private double value;
    private int quantity;
    
	public Product() {
	}

	public Product(String name, String description, double value, int quantity) {
		this.name = name;
		this.description = description;
		this.value = value;
		this.quantity = quantity;
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
}