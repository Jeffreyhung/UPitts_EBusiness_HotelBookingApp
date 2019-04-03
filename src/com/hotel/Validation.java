package com.hotel;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;


@ManagedBean(name="validation")
public class Validation {
	public static final Pattern regexEmail = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	public static final String regexName = "^[a-zA-Z0-9\\s\\-\\.]+$";
	public static final String regexAddress = "^[a-zA-Z0-9\\s\\-\\.\\,]+$";
	public Validation() {}
	
	public void validateName(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return;
		}
		String data = (String) value;
		if (!data.matches(regexName)) {
			FacesMessage message = new FacesMessage("Contains Invalid characters");
			throw new ValidatorException(message);
		}else if(data.length() > 20){
			FacesMessage message = new FacesMessage("Too long");
			throw new ValidatorException(message);
		}
	}
	
	public void validateEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return;
		}
		String data = (String) value;
		Matcher matcher = regexEmail .matcher(data);
	    matcher.find();
		if (!matcher.matches()) {
			FacesMessage message = new FacesMessage("Invalid email format");
			throw new ValidatorException(message);
		}else if(data.length() > 30){
			FacesMessage message = new FacesMessage("Too long");
			throw new ValidatorException(message);
		}
	}
	
	public void validateAddress(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			return;
		}
		String data = (String) value;
		if (!data.matches(regexAddress)) {
			FacesMessage message = new FacesMessage("Contains Invalid characters");
			throw new ValidatorException(message);
		}else if(data.length() > 50){
			FacesMessage message = new FacesMessage("Too long");
			throw new ValidatorException(message);
		}
	}
		
	public void validateDate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) return;
		try {
			List<Date> date = (List<Date>) value;
		}catch(IllegalArgumentException e) {
			FacesMessage message = new FacesMessage("Invalid input of Date");
			throw new ValidatorException(message);
		}
	}
	
	public void validateNum(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		if (value == null) return;
		
		int data = (int) value;
		
		if (data > 4 || data < 1) {
			FacesMessage message = new FacesMessage("Invalid number of People");
			throw new ValidatorException(message);
		}
	}
		
}
