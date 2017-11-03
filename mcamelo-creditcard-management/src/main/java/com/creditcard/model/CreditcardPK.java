package com.creditcard.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class CreditcardPK implements Serializable {

	private String number;

	private String username;

	public CreditcardPK() {
	}
	public String getNumber() {
		return this.number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CreditcardPK)) {
			return false;
		}
		CreditcardPK castOther = (CreditcardPK)other;
		return 
			this.number.equals(castOther.number)
			&& this.username.equals(castOther.username);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.number.hashCode();
		hash = hash * prime + this.username.hashCode();
		
		return hash;
	}
}