package com.hotel;

import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="availableRoom")
public class AvailableRoom {
	
	private String startDate;
	private String endDate;
	private int numOfPeople;
	private int selectedRid;
	
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
	
	public int getNumOfPeople() {
		return numOfPeople;
	}
	
	public void setNumOfPeople(int numOfPeople) {
		this.numOfPeople = numOfPeople;
	}
	
	public int getSelectedRid() {
		return selectedRid;
	}
	
	public void setSelectedRid(int selectedRid) {
		this.selectedRid = selectedRid;
	}

	public List<Room> getRooms() throws ClassNotFoundException, SQLException {
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
		PreparedStatement pstmt = connect.prepareStatement("SELECT RID, Name, Capacity, NumOfBeds, Price FROM room WHERE "
				+ "(RID NOT IN (SELECT RID FROM bookinginfo WHERE Start_Date >= ? or End_Date <= ? ))"
				+ " AND (Capacity >= ?)");
		pstmt.setString(1, getStartDate());
		pstmt.setString(2, getEndDate());
		pstmt.setInt(3, getNumOfPeople());
		ResultSet rs = pstmt.executeQuery();
		
		List<Room> rooms = new ArrayList<Room>();
		while (rs.next()) {
			Room room = new Room();
			room.setRid(rs.getInt("RID"));
			room.setRname(rs.getString("Name"));
			room.setRcapacity(rs.getInt("Capacity"));
			room.setRbeds(rs.getInt("NumOfBeds"));
			room.setRprice(rs.getInt("Price"));
			rooms.add(room);
		}
		rs.close();
		pstmt.close();
		connect.close();
		return rooms;

	}
}
