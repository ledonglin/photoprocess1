package com.dj.dy.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dj.dy.dao.CommentDao;
import com.dj.dy.entity.Comment;
import com.dj.dy.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public void addAgree(String commentId) {
		this.commentDao.addCommentAgree(commentId);
	}

	@Override
	public void addDisagree(String commentId) {
		this.commentDao.addCommentDisagree(commentId);
	}

	@Override
	public void rollAgree(String commentId) {
		this.commentDao.rollCommentAgree(commentId);
	}

	@Override
	public void rollDisAgree(String commentId) {
		this.commentDao.rollCommentDisagree(commentId);
	}

	@Override
	public void addComment(Comment comment) {
		this.commentDao.addComment(comment);
	}

	@Override
	public void replayComment(Comment comment, String replyToCommentId) {
		Comment replyToComment = this.commentDao.getCommentById(replyToCommentId);
		String commentContext = fillReplyCommentContext(replyToComment, comment.getCommentContext());
		comment.setCommentContext(commentContext);
		this.commentDao.addComment(comment);
	}

	/**
	 * 格式化信息 ：通过#开始标识回复的开始和结束，两个@@之间表示被回复信息的用户名
	 * eg.
	 * replyComment = {commentContext : "今天天气不错", replyToUserName : "钻石王老五"}
	 * commentContext = "你那里竟然没有雾霾？";
	 * 返回的信息为：#@钻石王老五@今天天气不错#你那里竟然没有雾霾？
	 * @param replyComment 回复的Comment信息
	 * @param commentContext 追加的回复信息
	 * @return 返回追加后经格式化的信息正文
	 */
	private String fillReplyCommentContext(Comment replyComment, String commentContext){
		String message = replyComment.getCommentContext();
		StringBuilder sb = new StringBuilder();
		sb.append("#");//reply message started key
		sb.append("@").append(replyComment.getReplyToUserName()).append("@"); 
		sb.append(replyComment.getCommentContext());
		sb.append("#");//reply message end key
		sb.append(message);
		return sb.toString();
	}
	
	@Override
	public Comment getCommentById(String commentId) {
		return this.commentDao.getCommentById(commentId);
	}

	@Override
	public List<Comment> getPicComments(String picId) {
		return this.commentDao.getPicComments(picId);
	}

	@Override
	public List<Comment> getCommentsByUserId(String userId) {
		return this.commentDao.getCommentsByUserId(userId);
	}

	@Override
	public List<Comment> getCommentsByFavourite() {
		return this.commentDao.getCommentsByFavourite();
	}

	@Override
	public List<Comment> getCommentsByFavourite(int limit) {
		return this.commentDao.getCommentsByFavourite(limit);
	}

	@Override
	public List<Comment> getPicComments(String picId, String order) {
		return this.commentDao.getPicComments(picId, order);
	}

	@Override
	public List<Comment> getCommentsByUserId(String userId, String order) {
		return this.commentDao.getCommentsByUserId(userId, order);
	}

	@Override
	public List<Comment> getCommentsByFavourite(int start, int limit) {
		return this.commentDao.getCommentsByFavourite(start, limit);
	}

	@Override
	public void deleteComment(String commentId) {
		this.commentDao.deleteComment(commentId);
	}

}
