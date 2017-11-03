package com.creditcard.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.creditcard.model.Creditcard;

@Repository
public class CreditCardDaoImpl implements CreditCardDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveCreditCard(Creditcard creditcard) throws Exception{
		sessionFactory.getCurrentSession().saveOrUpdate(creditcard);
	}
	
	public List<String> findAllCreditCardNumber() throws Exception{
		
		List<String> creditCardNumber = new ArrayList<String>();
		creditCardNumber = sessionFactory.getCurrentSession().createQuery("select c.id.number from Creditcard c").list();

		return creditCardNumber;
		
	}
	
	public List<String> findCreditCardNumberByUsername(String userName) throws Exception {
		
		List<String> creditCardNumber = new ArrayList<String>();
		creditCardNumber = sessionFactory.getCurrentSession().createQuery("select c.id.number from Creditcard c where c.id.username=?").setParameter(0, userName).list();

		return creditCardNumber;
	}
	
	public String findUsernameByNumber(String number) throws Exception{
		
		List<String> username = new ArrayList<String>();
		username = sessionFactory.getCurrentSession().createQuery("select c.id.username from Creditcard c where c.id.number=?").setParameter(0, number).list();

		return username.get(0);
	}
	
	public Creditcard getCreditCardInfo(String creditCardnumber) throws Exception{
		
		List<Creditcard> creditCard = new ArrayList<Creditcard>();
		creditCard = sessionFactory.getCurrentSession().createQuery("from Creditcard c where c.id.number=?").setParameter(0, creditCardnumber).list();

		return creditCard.get(0);
	}

}