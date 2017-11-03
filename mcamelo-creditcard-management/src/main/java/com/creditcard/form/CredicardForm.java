package com.creditcard.form;

import org.springframework.stereotype.Component;

@Component
public class CredicardForm {
	
	private String creditCardnumber;

	private String cardName;

	private String cvv;

	private String expiryDate;

	private String username;

	
	public CredicardForm() {
	}

	public String getCreditCardnumber() {
		return creditCardnumber;
	}

	public void setCreditCardnumber(String creditCardnumber) {
		this.creditCardnumber = creditCardnumber;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}
