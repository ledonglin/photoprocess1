package com.dj.dy.entity;

public class JsonEntity {

	private int messageCode;
	private String message;

	public JsonEntity() {
	}

	public JsonEntity(int messageCode, String message) {
		this.message = message;
		this.messageCode = messageCode;
	}

	public int getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(int messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
