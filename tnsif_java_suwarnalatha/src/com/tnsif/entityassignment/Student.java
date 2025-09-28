package com.tnsif.entityassignment;

public class Student {
	private String name;
	private int year;
	private String dept;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", year=" + year + ", dept=" + dept + ", getName()=" + getName()
				+ ", getYear()=" + getYear() + ", getDept()=" + getDept() + "]";
	}
    
}
