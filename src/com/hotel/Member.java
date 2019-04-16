package com.hotel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.hotel.DBConnection;
import com.hotel.SessionUtils;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="member")
@SessionScoped
public class Member {
	private int cid;
	private Customer customer;
	private String username;
	private String password;
	private String newPassword;
	
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
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
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
	    	this.cid = rs.getInt("CID");
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
			session.setAttribute("username", customer.getUsername());
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

	public void retrieve() throws ClassNotFoundException, SQLException {
		this.username = SessionUtils.getUserId();
		Connection connect = DBConnection.connectDB();
		PreparedStatement pstmt = connect.prepareStatement("SELECT * FROM customer where username = ?");
		pstmt.setString(1, username);
		ResultSet rs = pstmt.executeQuery();
		Customer tem = new Customer();
		if (rs.next() != false) {
			tem.setCid(rs.getInt("CID"));
			tem.setFirstName(rs.getString("FirstName"));
			tem.setLastName(rs.getString("LastName"));
			tem.setAddress(rs.getString("Address"));
			tem.setPhone(rs.getString("PhoneNum"));
			tem.setEmail(rs.getString("Email"));;
	    }
		this.customer = tem;
	}

	public String edit() throws ClassNotFoundException, SQLException {
		Connection connect = DBConnection.connectDB();
		boolean changePW = (newPassword != null);
		if(changePW) {
			PreparedStatement pstmt3 = connect.prepareStatement(
					"UPDATE customer SET FirstName = ?, LastName = ?, Address = ?, PhoneNum = ?, Email = ?, password = ?"
					+ "WHERE username = ?");
			pstmt3.setString(1, customer.getFirstName());
			pstmt3.setString(2, customer.getLastName());
			pstmt3.setString(3, customer.getAddress());
			pstmt3.setString(4, customer.getPhone());
			pstmt3.setString(5, customer.getEmail());
			pstmt3.setString(6, newPassword);
			pstmt3.setString(7, username);
			pstmt3.executeUpdate();
			pstmt3.close();
		}else {
			PreparedStatement pstmt3 = connect.prepareStatement(
					"UPDATE customer SET FirstName = ?, LastName = ?, Address = ?, PhoneNum = ?, Email = ?"
					+ "WHERE username = ?");
			pstmt3.setString(1, customer.getFirstName());
			pstmt3.setString(2, customer.getLastName());
			pstmt3.setString(3, customer.getAddress());
			pstmt3.setString(4, customer.getPhone());
			pstmt3.setString(5, customer.getEmail());
			pstmt3.setString(6, username);
			pstmt3.executeUpdate();
			pstmt3.close();
		}
		connect.close();
		return "memberInfo";
	}

	public List<BookingInfo> getBookingList() throws SQLException, ClassNotFoundException{
		Connection connect = DBConnection.connectDB();
		PreparedStatement pstmt = connect.prepareStatement("SELECT * FROM bookinginfo WHERE CID = ?");
		pstmt.setInt(1, cid);
		ResultSet rs = pstmt.executeQuery();

		List<BookingInfo> BIs = new ArrayList<BookingInfo>();
		while (rs.next()) {
			BookingInfo temp = new BookingInfo();
			temp.setBid(rs.getInt("BID"));
			temp.setRid(rs.getInt("RID"));
			temp.setStartDate(rs.getString("Start_Date"));
			temp.setEndDate(rs.getString("End_Date"));
			BIs.add(temp);
		}
		rs.close();
		pstmt.close();
		connect.close();
		return BIs;
	}
	
	public void cancel(int bid) throws IOException, ClassNotFoundException, SQLException {
		Connection connect = DBConnection.connectDB();
		PreparedStatement pstmt = connect.prepareStatement("DELETE FROM bookinginfo WHERE BID = ?");
		pstmt.setInt(1, bid);
		pstmt.executeUpdate();
		pstmt.close();
		connect.close();
		reload();
	}
	
	public void reload() throws IOException {
	    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
	}
}
