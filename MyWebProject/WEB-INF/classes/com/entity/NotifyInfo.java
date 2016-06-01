package com.entity;

public class NotifyInfo {
	private int writing_number;
	private String title;
	private String regDate;
	private String writer;
	private String content;
	
	public NotifyInfo(){
		
	}
	
	public NotifyInfo(int writing_number, String title, String regDate, String writer, String content) {
		super();
		this.writing_number = writing_number;
		this.title = title;
		this.regDate = regDate;
		this.writer = writer;
		this.content = content;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getwriting_Number() {
		return writing_number;
	}
	public void setwriting_Number(int writing_number) {
		this.writing_number = writing_number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
}
