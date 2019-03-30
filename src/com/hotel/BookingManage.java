package com.hotel;

import javax.faces.bean.ManagedBean;
import java.sql.*;

@ManagedBean(name="bookingManage")
public class BookingManage {
	
	private int rid;
	private String startDate;
	private String endDate;
	private Room room;
	
	public BookingManage() {}
	
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public int getRid() {
		return rid;
	}
	
	public void setRid(int rid) {
		this.rid = rid;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String select(int rid, String startDate, String endDate) {
		this.rid = rid;
		this.startDate = startDate;
		this.endDate = endDate;	
	    return "order?faces-redirect=true&includeViewParams=true";
	}
	
	public void onload()  throws ClassNotFoundException, SQLException {
		Connection connect = connectDB();
		PreparedStatement pstmt = connect.prepareStatement("SELECT RID, Name, Capacity, NumOfBeds, Price FROM room WHERE RID = ?");
		pstmt.setInt(1, getRid());
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			Room temp  = new Room();
			temp.setRid(rs.getInt("RID"));
			temp.setRname(rs.getString("Name"));
			temp.setRcapacity(rs.getInt("Capacity"));
			temp.setRbeds(rs.getInt("NumOfBeds"));
			temp.setRprice(rs.getInt("Price"));
			setRoom(temp);
		}
		rs.close();
		pstmt.close();
		connect.close();
	}
	
	public String addBooking(Customer cust) throws ClassNotFoundException, SQLException{
		Customer customer = cust;
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
		PreparedStatement pstmt2 = connect.prepareStatement("INSERT INTO customer VALUES (?, ?, ?, ?, ?, ?, ?) ");
		pstmt2.setInt(1, customer.getCid());
		pstmt2.setString(2, customer.getBookingName());
		pstmt2.setString(3, customer.getFirstName());
		pstmt2.setString(4, customer.getLastName());
		pstmt2.setString(5, customer.getAddress());
		pstmt2.setString(6, Integer.toString(customer.getPhone()));
		pstmt2.setString(7, customer.getEmail());
		pstmt2.executeUpdate();
		pstmt2.close();
		// Get customer ID
		PreparedStatement pstmt4 = connect.prepareStatement("SELECT MAX(bid) FROM bookinginfo");
		ResultSet rs2 = pstmt4.executeQuery();
		while (rs2.next()) {
			bookingID = rs2.getInt("MAX(bid)")+1;
		}
		rs.close();
		pstmt4.close();
		// Add value to bookingInfo table
		PreparedStatement pstmt3 = connect.prepareStatement("INSERT INTO bookinginfo VALUES (?, ?, ?, ?, ?) ");
		pstmt3.setInt(1, bookingID);
		pstmt3.setInt(2, customer.getCid());
		pstmt3.setInt(3, rid);
		pstmt3.setString(4, startDate);
		pstmt3.setString(5, endDate);
		pstmt3.executeUpdate();
		pstmt3.close();
		
		connect.close();
		
		
//		return "confirmation?faces-redirect=true";
		return "True";
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
