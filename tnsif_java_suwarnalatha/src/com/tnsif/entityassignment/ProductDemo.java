package com.tnsif.entityassignment;

public class ProductDemo {

	public static void main(String[] args) {
		Product p1 = new Product();
		p1.setName("Mouse");
		p1.setCost(500);
		p1.setQuantity(5);
		System.out.println(p1);
		
		Product p2 = new Product();
		p2.setName("Laptop");
		p2.setCost(75500);
		p2.setQuantity(5);
		System.out.println(p2);
		

	}

}

