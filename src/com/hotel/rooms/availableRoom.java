package com.hotel.rooms;

import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class availableRoom {
	public List<Room> getRooms() throws ClassNotFoundException, SQLException {
		Connection connect = null;
	
		String url = "jdbc:mysql://localhost:3306/test";
	
		String username = "root";
		String password = "root";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(url, username, password);
		} catch (SQLException ex) {
			System.out.println("in exec");
			System.out.println(ex.getMessage());
		}
		PreparedStatement pstmt = connect.prepareStatement("select ID, Name, Capacit, Beds, Price from rooms");
		ResultSet rs = pstmt.executeQuery();
		
		List<Room> Rooms = new ArrayList<Room>();
		while (rs.next()) {
			Room room = new Room();
			room.setRid(rs.getInt("ID"));
			room.setRname(rs.getString("Name"));
			room.setRcapacity(rs.getInt("Capacity"));
			room.setRbeds(rs.getInt("Beds"));
			room.setRprice(rs.getInt("Price"));
			Rooms.add(room);
		}
		rs.close();
		pstmt.close();
		connect.close();
		System.out.print(Rooms);
		return Rooms;

	}
}
