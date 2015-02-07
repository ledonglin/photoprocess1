package com.dj.dy.entity;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 评论类 如果是回复其他人的评论，则在commentsContext内存在@commentId@链接到原评论。
 * 同时replyToUser将会记录被评论的用户ID；如果是直接回复图片，则replyToUser为null
 * 
 * @author ledong
 *
 */
@Entity
@Table(name = "COMMENT")
public class Comment {

	private String commentId;

	private String commentUserId;
	private String commentUsername;
	private String commentPic;
	private String commentContext;
	private long agree;
	private long disagree;
	private String replyToUser;
	private String replyToUserName;
	private Date date;

	public Comment() {
	}

	public Comment(String commentId, String commentPic, String commentUserId,
			String commentUsername, String commentContext, String replyUser) {
		super();
		this.commentId = commentId;
		this.commentPic = commentPic;
		this.commentUserId = commentUserId;
		this.commentUsername = commentUsername;
		this.commentContext = commentContext;
		this.date = Calendar.getInstance().getTime();
	}

	public Comment(String commentId, String commentPic, String commentUserId,
			String commentUsername, String commentContext, long agree,
			long disagree, String replyUser, String replyToUser,
			String replyToUserName, Date date) {
		super();
		this.commentId = commentId;
		this.commentPic = commentPic;
		this.commentContext = commentContext;
		this.commentUserId = commentUserId;
		this.commentUsername = commentUsername;
		this.agree = agree;
		this.disagree = disagree;
		this.replyToUser = replyToUser;
		this.replyToUserName = replyToUserName;
		this.date = date;
	}

	@Id
	@Column(name = "commentId")
	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	@Column(name = "commentPic")
	public String getCommentPic() {
		return commentPic;
	}

	public void setCommentPic(String commentPic) {
		this.commentPic = commentPic;
	}

	@Column(name = "commentContext")
	public String getCommentContext() {
		return commentContext;
	}

	public void setCommentContext(String commentContext) {
		this.commentContext = commentContext;
	}

	@Column(name = "agree")
	public long getAgree() {
		return agree;
	}

	public void setAgree(long agree) {
		this.agree = agree;
	}

	@Column(name = "disagree")
	public long getDisagree() {
		return disagree;
	}

	public void setDisagree(long disagree) {
		this.disagree = disagree;
	}

	@Column(name = "replyToUser")
	public String getReplyToUser() {
		return replyToUser;
	}

	public void setReplyToUser(String replyToUser) {
		this.replyToUser = replyToUser;
	}

	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "replyToUsername")
	public String getReplyToUserName() {
		return replyToUserName;
	}

	public void setReplyToUserName(String replyToUserName) {
		this.replyToUserName = replyToUserName;
	}

	@Column(name = "commentUserId")
	public String getCommentUserId() {
		return commentUserId;
	}

	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}

	@Column(name = "commentUsername")
	public String getCommentUsername() {
		return commentUsername;
	}

	public void setCommentUsername(String commentUsername) {
		this.commentUsername = commentUsername;
	}

}
