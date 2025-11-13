package com.tnsif.entityassignment;

public class Product {
	private String name;
	private int cost;
	private int quantity;
	private String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Product [name=" + name + ", cost=" + cost + ", quantity=" + quantity + ", getName()=" + getName()
				+ ", getCost()=" + getCost() + ", getQuantity()=" + getQuantity() + "]";
	}
	

}


