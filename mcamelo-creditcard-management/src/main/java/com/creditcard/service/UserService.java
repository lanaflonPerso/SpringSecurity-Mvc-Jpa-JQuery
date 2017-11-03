package com.creditcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.creditcard.dao.UserDao;
import com.creditcard.model.User;
import com.creditcard.model.UserRole;

@Service("userManagementService")
@Transactional(rollbackFor={Exception.class})
public class UserService {

	@Autowired private UserDao userDao;

	
	public void saveNewUser(User user) throws Exception {
		
		user.setPassword(user.getPassword());
		user.setUsername(user.getUsername());
		user.setEnabled(true);
		userDao.saveUser(user);
		
		UserRole userRole = new UserRole();
		userRole.setRole("ROLE_USER");
		userRole.setUser(user);
		userDao.saveUserRole(userRole);
		
	}
	
}
