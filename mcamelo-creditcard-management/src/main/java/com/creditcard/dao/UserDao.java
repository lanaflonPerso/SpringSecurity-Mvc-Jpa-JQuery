package com.creditcard.dao;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.creditcard.model.User;
import com.creditcard.model.UserRole;

public interface UserDao {

	public User findByUserName(String username) throws UsernameNotFoundException;
	public void saveUser(User user) throws Exception;
	public void saveUserRole(UserRole userRole) throws Exception;

}