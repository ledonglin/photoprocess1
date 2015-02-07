package com.dj.dy.service;

import com.dj.dy.entity.LoginLog;
import com.dj.dy.entity.User;
import com.dj.dy.exception.UserExistException;

public interface LoginService {

	User userLogin(User user, LoginLog log);
	
	void save(User user) throws UserExistException;
	
	void updateUser(User user);
}
