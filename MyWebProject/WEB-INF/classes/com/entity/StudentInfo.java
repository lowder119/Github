package com.entity;

public class StudentInfo {
	private int studentCode;
	private String pw;
	private String name;
	private String major;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public StudentInfo(){}
	
	public int getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(int studentCode) {
		this.studentCode = studentCode;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}

	@Override
	public String toString() {
		return "StudentInfo [studentCode=" + studentCode + ", pw=" + pw + ", name=" + name + ", major=" + major + "]";
	}
	
}
