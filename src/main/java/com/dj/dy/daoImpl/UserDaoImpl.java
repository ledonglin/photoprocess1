package com.dj.dy.daoImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dj.dy.dao.BaseHibernateDAO;
import com.dj.dy.dao.UserDao;
import com.dj.dy.entity.User;


@Repository
public class UserDaoImpl extends BaseHibernateDAO implements UserDao {
	private final String GET_USER_BY_USERNAME = "from User u where u.userName = ?";

	private final String QUERY_USER_BY_USERNAME = "from User u where u.userName like ?";

	/**
	 * 根据用户名查询User对象
	 * 
	 * @param userName
	 *            用户名
	 * @return 对应userName的User对象，如果不存在，返回null。
	 */
	public User getUserByUserName(String userName) {

		Query queryString = getSession().createQuery(GET_USER_BY_USERNAME);

		List<User> users = (List<User>) queryString.setParameter(0, userName)
				.list();
		if (users == null || users.size() == 0) {
			return null;
		} else {
			return users.get(0);
		}
	}

	/**
	 * 根据用户名为模糊查询条件，查询出所有前缀匹配的User对象
	 * 
	 * @param userName
	 *            用户名查询条件
	 * @return 用户名前缀匹配的所有User对象
	 */
	public List<User> queryUserByUserName(String userName) {
		Query queryString = getSession().createQuery(QUERY_USER_BY_USERNAME);

		return ((List<User>) queryString.setParameter(0, userName).list());
	}

	@Override
	public User findUserByLoginName(String username) {
		List<User> userList = getSession().createCriteria(User.class)
				.add(Restrictions.eq("userName", username)).list();
		if (userList == null || userList.size() == 0)
			return null;
		else
			return userList.get(0);

	}

	/**
	 * 更新用户
	 * 
	 * @param user
	 */
	public void update(User user) {
		getSession().saveOrUpdate(user);
	}

	@Override
	public void regeistUser(User user) {
		getSession().save(User.class.getName(), user);
	}
}
