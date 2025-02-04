package com.hotel;

import com.hotel.DBConnection;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@ManagedBean(name="availableRoom")
@SessionScoped
public class AvailableRoom {
	
	private String startDate;
	private String endDate;
	private int numOfPeople;
	private int selectedRid;
	private List<Date> range;
	private Date todayDate = new Date();
	
	public Date getTodayDate() {
	    return todayDate;
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

	public List<Date> getRange() {
        return range;
    }
 
    public void setRange(List<Date> range) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	setStartDate(sdf.format(range.get(0)));
    	setEndDate(sdf.format(range.get(1)));
        this.range = range;
    }
	
	public List<Room> getRooms() throws ClassNotFoundException, SQLException {
		Connection connect = DBConnection.connectDB();

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

	public String run() {
		return "availableRooms?faces-redirect=true&includeViewParams=true";
	}
}
