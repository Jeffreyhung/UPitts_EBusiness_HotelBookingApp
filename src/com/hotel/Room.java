package com.hotel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class Room {

	private Integer rid;
	private String rname;
	private Integer rcapacity;
	private Integer rbeds;
	private Integer rprice;

	public Room() {
	}

	public Room(Integer rid, String rname, Integer rcapacity, Integer rbeds, Integer rprice) {
		this.rid = rid;
		this.rname = rname;
		this.rcapacity = rcapacity;
		this.rbeds = rbeds;
		this.rprice = rprice;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public Integer getRcapacity() {
		return rcapacity;
	}

	public void setRcapacity(Integer rcapacity) {
		this.rcapacity = rcapacity;
	}

	public Integer getRbeds() {
		return rbeds;
	}

	public void setRbeds(Integer rbeds) {
		this.rbeds = rbeds;
	}

	public Integer getRprice() {
		return rprice;
	}

	public void setRprice(Integer rprice) {
		this.rprice = rprice;
	}

}

