package com.dj.dy.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.dy.dao.LoginLogDao;
import com.dj.dy.dao.UserDao;
import com.dj.dy.entity.User;
import com.dj.dy.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private LoginLogDao loginLogDao;

	@Override
	public User findUserbyLoginName(User user) {
		return userDao.findUserByLoginName(user.getUserName());
	}

}
