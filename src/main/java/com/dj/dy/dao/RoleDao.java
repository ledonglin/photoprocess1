package com.dj.dy.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dj.dy.entity.Role;


@Repository
public class RoleDao extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(RoleDao.class);

	public Role getRoleById(String roleId) {
		log.debug("invoke method getRoleById start ...");
		Role role = (Role) getSession().get(Role.class, roleId);
		log.debug("invoke method getRoleById end...");

		return role;
	}

	public void saveRole(Role role) {
		log.debug("invoke method saveRole start ...");
		getSession().save(role);
		log.debug("invoke method saveRole end...");
	}
	
	public void modifyRoleName(Role role) {
		log.debug("invoke method modifyRoleName start ...");
		getSession().saveOrUpdate(role);
		log.debug("invoke method modifyRoleName end...");

	}
	public void addNewRole(Role role) {
		log.debug("invoke method addNewRole start ...");
		getSession().save(role);
		log.debug("invoke method addNewRole end ...");

	}
}
