package com.dj.dy.daoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dj.dy.dao.BaseHibernateDAO;
import com.dj.dy.dao.LoginLogDao;
import com.dj.dy.entity.LoginLog;


@Repository
public class LoginLogDaoImpl extends BaseHibernateDAO implements LoginLogDao {
	private static final Logger logger = LoggerFactory
			.getLogger(LoginLogDaoImpl.class);

	@Override
	public void login(LoginLog log) {
		logger.debug("login start...");
		getSession().save(log);
		logger.debug("login end...");
	}

}
