package com.hotel;

import javax.faces.bean.ManagedBean;
import java.sql.*;

@ManagedBean(name="bookingManage")
public class BookingManage {
	
	private int rid;
	private String startDate;
	private String endDate;
	private BookingInfo bookingInfo;
	private Room room;
	
	public BookingManage() {}
	
	public BookingInfo getBookingInfo() {
		return bookingInfo;
	}
	
	public void setBookingInfo(BookingInfo bookingInfo) {
		this.bookingInfo = bookingInfo;
	}
	
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
	
	public String addBooking(Customer customer) {
		System.out.println("TEST2");
		
//		return "confirmation?faces-redirect=true";
		return "True";
	}
	
}
