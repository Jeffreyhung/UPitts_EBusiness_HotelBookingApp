package com.hotel;

public class BookingInfo {
	private int bid;
	private int cid;
	private int rid;
	private String startDate;
	private String endDate;
	
	public BookingInfo() {}
	
	public BookingInfo(int bid, int cid, int rid, String startDate, String endDate) {
		this.bid = bid;
		this.rid = rid;
		this.cid = cid;
		this.startDate = startDate;
		this.endDate = endDate;
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
}
