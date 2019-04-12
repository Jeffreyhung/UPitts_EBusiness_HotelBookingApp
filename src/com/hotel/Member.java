package com.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.hotel.DBConnection;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import com.hotel.SessionUtils;

@ManagedBean(name="member")
@SessionScoped
public class Member {
	private Customer customer;
	private String username;
	private String password;
	
	public Member() {}
	
	public Member(Customer customer) {
		this.customer = customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
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
	
	public String login() throws ClassNotFoundException, SQLException {
		boolean valid = loginValidation();
		if (valid) {
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", username);
			return "index";
		} else {
			FacesContext.getCurrentInstance().addMessage("signIn:password", new FacesMessage("Invalid Username and Password"));
			return "signIn";
		}
	}
	
	public boolean loginValidation() throws ClassNotFoundException, SQLException {
		Connection connect = DBConnection.connectDB();
		PreparedStatement pstmt = connect.prepareStatement("SELECT * FROM customer where username = ?");
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next() == false) {
	        return false;
	    }else {
	    	String correctPW = rs.getString("password");
	    	if (password.equals(correctPW)) {
	    		return true;
	    	}
	    	else {
	    		return false;
	    	}
	    }
	}
	
	public String signUp(Customer customer) throws ClassNotFoundException, SQLException {
		Connection connect = DBConnection.connectDB();
		int customerID = 0;
		if(signupValidation(customer.getUsername(), customer.getPassword(), connect)) {
		// Get customer ID
			PreparedStatement pstmt = connect.prepareStatement("SELECT MAX(cid) FROM customer");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				customerID = rs.getInt("MAX(cid)")+1;
			}
			rs.close();
			pstmt.close();
			customer.setCid(customerID);
		// Add value to customer table
			PreparedStatement pstmt2 = connect.prepareStatement("INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");
			pstmt2.setInt(1, customer.getCid());
			pstmt2.setString(2, customer.getFirstName());
			pstmt2.setString(3, customer.getLastName());
			pstmt2.setString(4, customer.getAddress());
			pstmt2.setString(5, customer.getPhone());
			pstmt2.setString(6, customer.getEmail());
			pstmt2.setString(7, customer.getUsername());
			pstmt2.setString(8, customer.getPassword());
			pstmt2.executeUpdate();
			pstmt2.close();
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", username);
			return "index";
		}else {
			FacesContext.getCurrentInstance().addMessage("signUp:username", new FacesMessage("Username already in use, please choose another username"));
			return "signUp";
		}
	}
	
	public boolean signupValidation(String username, String password, Connection connect) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = connect.prepareStatement("SELECT * FROM customer where username = ?");
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		//check if username is already in use, if username is used, return false
		if (rs.next() == false) {
	        return true;
	    }else {
	    	return false;
	    }
	}
	
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "index";
	}
}
