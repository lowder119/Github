package com.entity;

public class RentalRoom {
	private String rental_date;
	private String rental_time;
	private String roomcode;
	private int studentcode;
	private int persons;
	private String studentcodes;
	private String reason;
	
	public RentalRoom(){}

	public RentalRoom(String rental_date, String rental_time, String roomcode, int studentcode, int persons,
			String studentcodes, String reason) {
		super();
		this.rental_date = rental_date;
		this.rental_time = rental_time;
		this.roomcode = roomcode;
		this.studentcode = studentcode;
		this.persons = persons;
		this.studentcodes = studentcodes;
		this.reason = reason;
	}

	public String getRental_date() {
		return rental_date;
	}

	public void setRental_date(String rental_date) {
		this.rental_date = rental_date;
	}

	public String getRental_time() {
		return rental_time;
	}

	public void setRental_time(String rental_time) {
		this.rental_time = rental_time;
	}

	public String getRoomcode() {
		return roomcode;
	}

	public void setRoomcode(String roomcode) {
		this.roomcode = roomcode;
	}

	public int getStudentcode() {
		return studentcode;
	}

	public void setStudentcode(int studentcode) {
		this.studentcode = studentcode;
	}

	public int getPersons() {
		return persons;
	}

	public void setPersons(int persons) {
		this.persons = persons;
	}

	public String getStudentcodes() {
		return studentcodes;
	}

	public void setStudentcodes(String studentcodes) {
		this.studentcodes = studentcodes;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
