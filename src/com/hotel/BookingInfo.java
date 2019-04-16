package com.hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name="bookingInfo")
@SessionScoped
public class BookingInfo {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private int bid;
	private int cid;
	private int rid;
	private String startDate;
	private String endDate;
	private Room room;
	private boolean past;
	
	public BookingInfo() {}
	
	public BookingInfo(int bid, int cid, int rid, String startDate, String endDate, Room room) {
		this.bid = bid;
		this.rid = rid;
		this.cid = cid;
		this.startDate = startDate;
		this.endDate = endDate;
		this.room = room;
	}
	
	public int getBid() {
		return bid;
	}
	
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	public int getCid() {
		return cid;
	}
	
	public void setCid(int cid) {
		this.cid = cid;
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
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}	

	public Room getRoom() throws ClassNotFoundException, SQLException {
		Connection connect = DBConnection.connectDB();
		PreparedStatement pstmt = connect.prepareStatement("SELECT * FROM room WHERE RID = ?");
		pstmt.setInt(1, getRid());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			Room temp  = new Room();
			temp.setRid(rs.getInt("RID"));
			temp.setRname(rs.getString("Name"));
			temp.setRcapacity(rs.getInt("Capacity"));
			temp.setRbeds(rs.getInt("NumOfBeds"));
			temp.setRprice(rs.getInt("Price"));
			this.room = temp;
		}
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
	
	public boolean getPast() throws ParseException {
		Date start = sdf.parse(getStartDate());
		Date today = new Date();
		if (today.compareTo(start) >= 0) {
			return true;
		}else {
			return false;
		}
	}
}
