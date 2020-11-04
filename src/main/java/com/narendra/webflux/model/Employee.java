package com.narendra.webflux.model;

public class Employee {

	private String name;
	private int rollno;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRollno() {
		return rollno;
	}
	public void setRollno(int rollno) {
		this.rollno = rollno;
	}
	
	public Employee() {
	}
	
	public Employee(String name, int rollno) {
		super();
		this.name = name;
		this.rollno = rollno;
	}
	
	
}
