package com.hotel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean(name="sessionUtils")
@SessionScoped
public class SessionUtils {
	private boolean status = true;

	public static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
	}

	public static String getUserName() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return session.getAttribute("username").toString();
	}

	public static String getUserId() {
		HttpSession session = getSession();
		if (session != null)
			return (String) session.getAttribute("username");
		else {
			return null;
		}
	}
	
	public boolean getStatus() {
		if (getUserId() != null) {
			this.status = true;
			return true;
		}else {
			this.status = false;
			return false;
		}
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
}