package com.hotel;

import javax.faces.bean.*;
import com.hotel.DBConnection;
import com.hotel.SessionUtils;

import java.sql.*;

@ManagedBean(name="bookingManage")
@SessionScoped
public class BookingManage {
	private int rid;
	private String startDate;
	private String endDate;
	private Room room;
	private Customer customer;
	
	public BookingManage() {}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setRid(int rid) {
		this.rid = rid;
	}
	
	public int getRid() {
		return rid;
	}
		
	public String select(int rid, String startDate, String endDate) {
		this.rid = rid;
		this.startDate = startDate;
		this.endDate = endDate;	
	    return "order?faces-redirect=true&includeViewParams=true";
	}
	
	public void onload()  throws ClassNotFoundException, SQLException {
		Connection connect = DBConnection.connectDB();
		if (SessionUtils.getUserId()!=null) {
			PreparedStatement pstmt0 = connect.prepareStatement("SELECT * FROM customer WHERE username = ?");
			pstmt0.setString(1, SessionUtils.getUserId());
			ResultSet rs0 = pstmt0.executeQuery();
			if(rs0.next()) {
				Customer tem = new Customer();
				tem.setCid(rs0.getInt("CID"));
				tem.setFirstName(rs0.getString("FirstName"));
				tem.setLastName(rs0.getString("LastName"));
				tem.setPhone(rs0.getString("PhoneNum"));
				tem.setAddress(rs0.getString("Address"));
				tem.setEmail(rs0.getString("Email"));
				this.customer = tem;
			}
			rs0.close();
			pstmt0.close();
		}
		
	// Get Room info
		PreparedStatement pstmt = connect.prepareStatement("SELECT * FROM room WHERE RID = ?");
		pstmt.setInt(1, getRid());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Room temp  = new Room();
			temp.setRid(rs.getInt("RID"));
			temp.setRname(rs.getString("Name"));
			temp.setRcapacity(rs.getInt("Capacity"));
			temp.setRbeds(rs.getInt("NumOfBeds"));
			temp.setRprice(rs.getInt("Price"));
			this.setRoom(temp);
		}
		rs.close();
		pstmt.close();
		connect.close();
	}
	
	public String addBooking(Customer cust) throws ClassNotFoundException, SQLException{
		setCustomer(cust);
		int customerID = 0;
		int bookingID = 0;
		Connection connect = DBConnection.connectDB();
		
	// Get customer ID
		if (SessionUtils.getUserId() ==null) {
			PreparedStatement pstmt = connect.prepareStatement("SELECT MAX(cid) FROM customer");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				customerID = rs.getInt("MAX(cid)")+1;
			}
			rs.close();
			pstmt.close();
			customer.setCid(customerID);
			
			// Add value to customer table
			PreparedStatement pstmt2 = connect.prepareStatement("INSERT INTO customer (CID, FirstName, LastName, Address, PhoneNum, Email)VALUES (?, ?, ?, ?, ?, ?) ");
			pstmt2.setInt(1, customer.getCid());
			pstmt2.setString(2, customer.getFirstName());
			pstmt2.setString(3, customer.getLastName());
			pstmt2.setString(4, customer.getAddress());
			pstmt2.setString(5, customer.getPhone());
			pstmt2.setString(6, customer.getEmail());
			pstmt2.executeUpdate();
			pstmt2.close();
		}
		
	// Get booking ID
		PreparedStatement pstmt3 = connect.prepareStatement("SELECT MAX(bid) FROM bookinginfo");
		ResultSet rs2 = pstmt3.executeQuery();
		while (rs2.next()) {
			bookingID = rs2.getInt("MAX(bid)")+1;
		}
		rs2.close();
		pstmt3.close();
	// Add value to bookingInfo table
		PreparedStatement pstmt4 = connect.prepareStatement("INSERT INTO bookinginfo VALUES (?, ?, ?, ?, ?) ");
		pstmt4.setInt(1, bookingID);
		pstmt4.setInt(2, customer.getCid());
		pstmt4.setInt(3, rid);
		pstmt4.setString(4, startDate);
		pstmt4.setString(5, endDate);
		pstmt4.executeUpdate();
		pstmt4.close();
		connect.close();
	// Get room info
		onload();
	//redirect to confirmation page
		return "confirmation";
	}
	
}
