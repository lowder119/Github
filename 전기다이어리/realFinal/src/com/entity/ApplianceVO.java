package com.entity;

public class ApplianceVO {
	private int ItemNo;
	private String Item_Name;
	private int Power_Consumption;
	private int num;
	private int hour;
	private String period;
	private int Month_Consume_Hour;
	private double Month_Consume_Power;
	
	public ApplianceVO(){
		this.num = 1;
		this.hour = 1;
		this.period = "¿œ¿œ";
		this.Month_Consume_Hour = 30*this.getHour();
		this.Month_Consume_Power = 0;
	}
	
	public int getItemNo() {
		return ItemNo;
	}

	public void setItemNo(int ItemNo) {
		this.ItemNo = ItemNo;
	}
	

	public String getItem_Name() {
		return Item_Name;
	}

	public void setItem_Name(String item_Name) {
		Item_Name = item_Name;
	}

	public int getPower_Consumption() {
		return Power_Consumption;
	}

	public void setPower_Consumption(int power_Consumption) {
		Power_Consumption = power_Consumption;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getMonth_Consume_Hour() {
		return Month_Consume_Hour;
	}

	public void setMonth_Consume_Hour(int month_Consume_Hour) {
		Month_Consume_Hour = month_Consume_Hour;
	}

	public double getMonth_Consume_Power() {
		return Month_Consume_Power;
	}

	public void setMonth_Consume_Power(int Power_Consumption) {
		this.Month_Consume_Power = Power_Consumption * this.getNum() *this.getHour() * 30  / (double)1000;
	}
	
}
