package com.creditcard.dao;

import java.util.List;
import com.creditcard.model.Creditcard;

public interface CreditCardDao {

	public void saveCreditCard(Creditcard creditcard) throws Exception;
	public List<String> findAllCreditCardNumber() throws Exception;
	public List<String> findCreditCardNumberByUsername(String userName) throws Exception;
	public Creditcard getCreditCardInfo(String creditCardnumber) throws Exception;
	public String findUsernameByNumber(String number) throws Exception;

}