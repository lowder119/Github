package com.entity;

public class LoginVO {
	private String id, passWord;

	public LoginVO() {
		super();
	}
	
	public LoginVO(String id, String passWord) {
		super();
		this.id = id;
		this.passWord = passWord;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
