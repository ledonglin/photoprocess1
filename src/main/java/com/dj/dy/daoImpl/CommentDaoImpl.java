package com.dj.dy.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.dj.dy.dao.BaseHibernateDAO;
import com.dj.dy.dao.CommentDao;
import com.dj.dy.entity.Comment;

@Repository
public class CommentDaoImpl extends BaseHibernateDAO implements CommentDao {
	private final String UPDATE_AGREE_MOUNT = "update Comment c set c.agree = c.agree+1 where c.commentId = ?";
	private final String UPDATE_DISAGREE_MOUNT = "update Comment c set c.disagree = c.disagree+1 where c.commentId = ?";
	private final String ROLL_AGREE_MOUNT = "update Comment c set c.agree = c.agree-1 where c.commentId = ?";
	private final String ROLL_DISAGREE_MOUNT = "update Comment c set c.disagree = c.disagree-1 where c.commentId = ?";
	private final String DELETE_COMMENT = "delete Comment c where c.commentId = ?";

	@Override
	public void addComment(Comment comment) {
		getSession().save(comment);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Comment getCommentById(String commentId) {
		List<Comment> commentList = getSession().createCriteria(Comment.class)
				.add(Restrictions.eq("commentId", commentId)).list();
		if (commentList == null || commentList.size() == 0)
			return null;
		else
			return commentList.get(0);
	}

	/**
	 * 通过图片Id查找评论。默认是升序排列
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getPicComments(String picId) {
		List<Comment> commentList = getSession().createCriteria(Comment.class)
				.add(Restrictions.eq("picId", picId)).addOrder(Order.asc("date")).list();
		if (commentList == null )
			return new ArrayList<Comment>();
		else
			return commentList;
	}
	
	/**
	 * 通过图片Id查找评论。Order(asc)升序 Order(desc)降序
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getPicComments(String picId, String order) {
		
		Order orderType = "desc".equalsIgnoreCase(order) ? Order.desc("date") : Order.asc("date");
		
		List<Comment> commentList = getSession().createCriteria(Comment.class)
				.add(Restrictions.eq("picId", picId)).addOrder(orderType).list();
		if (commentList == null )
			return new ArrayList<Comment>();
		else
			return commentList;
	}

	/**
	 * 通过用户Id查找评论。默认降序
	 */
	@Override
	public List<Comment> getCommentsByUserId(String userId) {
		return getCommentsByUserId(userId,"desc");
	}
	/**
	 * 通过用户Id查找评论。Order(asc)升序 Order(desc)降序
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentsByUserId(String userId, String order) {
		Order orderType = "desc".equalsIgnoreCase(order) ? Order.desc("date") : Order.asc("date");

		List<Comment> commentList = getSession().createCriteria(Comment.class)
				.add(Restrictions.eq("userId", userId)).addOrder(orderType).list();
		
		if (commentList == null )
			return new ArrayList<Comment>();
		else
			return commentList;
	}

	/**
	 * 获取最精彩的评论
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentsByFavourite() {
		List<Comment> commentList = getSession().createCriteria(Comment.class)
				.addOrder(Order.desc("agree")).list();
		
		if (commentList == null )
			return new ArrayList<Comment>();
		else
			return commentList;
	}

	/**
	 * 获取最精彩的评论
	 * @param start 开始条数
	 * @param limit 限制条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> getCommentsByFavourite(int start, int limit) {
		List<Comment> commentList = getSession().createCriteria(Comment.class)
				.addOrder(Order.desc("agree")).setFirstResult(start).setFetchSize(limit).list();
		
		if (commentList == null )
			return new ArrayList<Comment>();
		else
			return commentList;
	}

	@Override
	public List<Comment> getCommentsByFavourite(int limit) {
		return getCommentsByFavourite(0, limit);
	}

	@Override
	public void addCommentAgree(String commentId) {
		getSession().createQuery(UPDATE_AGREE_MOUNT).setParameter(0, commentId).executeUpdate();
	}

	@Override
	public void addCommentDisagree(String commentId) {
		getSession().createQuery(UPDATE_DISAGREE_MOUNT).setParameter(0, commentId).executeUpdate();
		
	}

	@Override
	public void rollCommentAgree(String commentId) {
		getSession().createQuery(ROLL_AGREE_MOUNT).setParameter(0, commentId).executeUpdate();
		
	}

	@Override
	public void rollCommentDisagree(String commentId) {
		getSession().createQuery(ROLL_DISAGREE_MOUNT).setParameter(0, commentId).executeUpdate();
		
	}

	@Override
	public void deleteComment(String commentId) {
		getSession().createQuery(DELETE_COMMENT).setParameter(0, commentId).executeUpdate();
	}

}
