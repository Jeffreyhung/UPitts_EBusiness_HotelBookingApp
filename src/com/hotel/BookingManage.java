package com.hotel;

import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="bookingManage")
public class BookingManage {
	
	public String addBooking() {
		
		
		return "confirmation?faces-redirect=true";
	}
	
}
