package com.dj.dy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Picture")
public class Picture {

	private String pictureId;
	private String userId;
	private String username;
	private String message;
	private long agree;
	private long disagree;
	private Date date;

	public Picture() {
	}

	public Picture(String pictureId, String userId, String username, String message, long agree,
			long disagree, Date date) {
		super();
		this.pictureId = pictureId;
		this.userId = userId;
		this.username = username;
		this.message = message;
		this.agree = agree;
		this.disagree = disagree;
		this.date = date;
	}

	@Id
	@Column(name = "pictureId")
	public String getPictureId() {
		return pictureId;
	}

	public void setPictureId(String pictureId) {
		this.pictureId = pictureId;
	}

	@Column(name = "userId")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	@Column(name = "date")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
