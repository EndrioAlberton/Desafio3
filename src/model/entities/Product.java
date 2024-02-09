package model.entities;

import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L; 
    private int id;
    private String name;
	private String description;
    private double value;
    private int quantity;
	private String voltage;
	private String brand;
    
	public Product() {
	}
	
	public Product(String name, String description, double value, int quantity, String voltage, String brand) {
		this.name = name;
		this.description = description;
		this.value = value;
		this.quantity = quantity;
		this.voltage = voltage;
		this.brand = brand;
	}

	public Product(int id, String name, String description, double value, int quantity) {
		this.id = id;
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
//<<<<<<< HEAD
    
//=======

    public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		if (voltage.equals("110V") || voltage.equals("220V") || voltage.equals("BIVOLT")) {
            this.voltage = voltage;
        } else {
            throw new IllegalArgumentException("Invalid voltage");
        }
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

//>>>>>>> 6dfd8aee4860edce3ef463949ae3d9f5255fd75d
    public String toString() {
        return "{\n" +
                " \"id\": " + id + ",\n" +
                " \"name\": \"" + name + "\",\n" +
                " \"description\": \"" + description + "\",\n" +
                " \"value\": " + value + ",\n" +
                " \"quantity\": " + quantity + ",\n" +
                " \"voltage\": \"" + voltage + "\",\n" + 
                " \"brand\": \"" + brand + "\"\n" + 
                "}";
    }
//<<<<<<< HEAD
}   
//=======
	
//}
//>>>>>>> 6dfd8aee4860edce3ef463949ae3d9f5255fd75d
