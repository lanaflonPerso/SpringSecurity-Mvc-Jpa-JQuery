package com.creditcard.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.creditcard.dao.CreditCardDao;
import com.creditcard.form.CredicardForm;
import com.creditcard.model.Creditcard;
import com.creditcard.model.CreditcardPK;

@Service("creditCardManagementService")
@Transactional(rollbackFor={Exception.class})
public class CreditCardService {

	@Autowired private CreditCardDao creditCardDao;

	
	public void saveCreditCard(CredicardForm credicardForm, HttpServletRequest request) throws Exception {
		
	    String dateInString = "01-"+credicardForm.getExpiryDate().replaceAll("\\s", "").replace("/", "-");
        DateFormat formatter ; 
        Date date ; 
        formatter = new SimpleDateFormat("dd-MM-yy");
        date = formatter.parse(dateInString);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String creditCardnumber = credicardForm.getCreditCardnumber().replaceAll("\\s", "");
		
		Creditcard creditcard = new Creditcard();
		CreditcardPK creditcardPK = new CreditcardPK();
		creditcardPK.setNumber(creditCardnumber);
		
		if(auth.getAuthorities().toString().contains("ROLE_ADMIN")){
			creditcardPK.setUsername(findUsernameByNumber(creditCardnumber));
		}else{
			creditcardPK.setUsername(request.getUserPrincipal().getName());
		}
		
		creditcard.setCardName(credicardForm.getCardName());
		creditcard.setCvv(Integer.valueOf(credicardForm.getCvv()));
		creditcard.setExpiryDate(date);
		creditcard.setId(creditcardPK);
		
		creditCardDao.saveCreditCard(creditcard);
		
	}
	
	public List<String> findAllCreditCardNumber() throws Exception {
		
		List<String> creditCardNumber = creditCardDao.findAllCreditCardNumber();
		
		return creditCardNumber;
	}
	
	public List<String> findCreditCardNumberByUsername(String userName) throws Exception {
		
		List<String> creditCardNumber = creditCardDao.findCreditCardNumberByUsername(userName);
		
		return creditCardNumber;
		
	}
	
	public String findUsernameByNumber(String number) throws Exception {
		
		String creditCardNumber = creditCardDao.findUsernameByNumber(number);
		
		return creditCardNumber;
		
	}
	
	public Creditcard getCreditCardInfo(String creditCardnumber) throws Exception {
		
		return creditCardDao.getCreditCardInfo(creditCardnumber);
		
	}
	
}
