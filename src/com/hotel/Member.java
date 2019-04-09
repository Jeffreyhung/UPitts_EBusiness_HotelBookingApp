package com.hotel;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Member {
	private Customer customer;
	private String username;
	private String password;
	
	public Member() {}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return password;
	}
	
}
