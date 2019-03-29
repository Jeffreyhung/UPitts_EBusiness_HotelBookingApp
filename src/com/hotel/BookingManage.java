package com.hotel;

import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name="bookingManage")
public class BookingManage {
	
	private int rid;
	private String startDate;
	private String endDate;
	private BookingInfo bookingInfo;
	
	public BookingManage() {}
	
	public BookingInfo getBookingInfo() {
		return bookingInfo;
	}
	
	public void setBookingInfo(BookingInfo bookingInfo) {
		this.bookingInfo = bookingInfo;
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
	
	public void setEndDate() {
		
	}
	
	public String select(int rid, String startDate, String endDate) {
//		System.out.println(rid);
		this.rid = rid;
//		this.bookingInfo.setRid(this.rid);
		System.out.print(rid + startDate+ endDate);
		this.startDate = startDate;
//		this.bookingInfo.setStartDate(this.startDate);
		this.endDate = endDate;	
//		this.bookingInfo.setEndDate(this.endDate);
	    return "order";
	}
	
	public void onload() {
		System.out.println("onload success");
		System.out.println(this.rid);
		System.out.println(this.startDate);
		System.out.println(this.endDate);
	}
	
	public String addBooking() {
		return "confirmation?faces-redirect=true";
	}
	
}
