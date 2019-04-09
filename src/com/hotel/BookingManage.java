package com.hotel;

import javax.faces.bean.ManagedBean;
import java.sql.*;

@ManagedBean(name="bookingManage")
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
		Connection connect = connectDB();
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
		Connection connect = connectDB();
		
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
		PreparedStatement pstmt2 = connect.prepareStatement("INSERT INTO customer (CID, FirstName, LastName, Address, PhoneNum, Email)VALUES (?, ?, ?, ?, ?, ?) ");
		pstmt2.setInt(1, customer.getCid());
		pstmt2.setString(2, customer.getFirstName());
		pstmt2.setString(3, customer.getLastName());
		pstmt2.setString(4, customer.getAddress());
		pstmt2.setString(5, customer.getPhone());
		pstmt2.setString(6, customer.getEmail());
		pstmt2.executeUpdate();
		pstmt2.close();
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
	
	private Connection connectDB()  throws ClassNotFoundException{
		Connection connect = null;
		String url = "jdbc:mysql://localhost:3306/hotel";
		String username = "root";
		String password = "root";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(url, username, password);
		} catch (SQLException ex) {
			System.out.println("in exec");
			System.out.println(ex.getMessage());
		}
		return connect;
	}
	
}
