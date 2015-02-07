package com.dj.dy.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "USER")
public class User {

	private String userId;
	private String userName;
	private String password;
	private String email;
	// private Role role;
	private String mobilePhone;
	private String phone;
	private boolean sex;
	private String title;
	private Timestamp lastModified;

	@Id
	@Column(name = "USER_ID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "USERNAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JsonIgnore
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// public Role getRole() {
	// return role;
	// }
	//
	// public void setRole(Role role) {
	// this.role = role;
	// }

	@Column(name = "MOBILEPHONE")
	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "SEX")
	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "LASTMODIFIED")
	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User[");
		sb.append("username : " + this.getUserName());
		sb.append(", email : " + this.getEmail());
		sb.append(", phone : " + this.getMobilePhone());
		sb.append(", title : " + this.getTitle());
		sb.append("]");
		return sb.toString();
	}

}
