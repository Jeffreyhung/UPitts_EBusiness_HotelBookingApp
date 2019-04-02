package com.hotel;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Customer {
	private int cid;
	private String bookingName;
	private String firstName;
	private String lastName;
	private String address;
	private String phone;
	private String email;
	
	public Customer() {}
	
	public Customer(int cid, String bookingName, String firstName, String lastName, String address, String email, String phone) {
		this.cid = cid;
		this.bookingName = bookingName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}
	
	public int getCid() {
		return cid;
	}
	
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public String getBookingName() {
		return bookingName;
	}
	
	public void setBookingName(String bookingName) {
		this.bookingName = bookingName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
}
