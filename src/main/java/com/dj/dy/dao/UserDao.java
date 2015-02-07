package com.dj.dy.dao;

import com.dj.dy.entity.User;


public interface UserDao {

	public User findUserByLoginName(String loginName);

	public void regeistUser(User user);

	public void update(User user);

}
