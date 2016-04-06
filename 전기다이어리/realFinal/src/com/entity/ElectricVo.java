package com.entity;

import java.util.Calendar;
import java.util.Date;

public class ElectricVo {
	private Date d;
	private String id;
	public ElectricVo(){
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	private double used;
	public Date getD() {
		return d;
	}
	public void setD(Date d) {
		this.d = d;
	}
	public double getUsed() {
		return used;
	}
	public void setUsed(double used) {
		this.used = used;
	}
	public ElectricVo(String id,Date d,double used){
		this.id = id;
		this.d=d;
		this.used=used;
	}
	public static int LastDay(String s){		// YY/MM/DD

		
				
		String[] d=null;	//=date.split("/");// YYYY/MM/DD
		if(s.contains("/")){
			d=s.split("/");
		}else if(s.contains("-")){
			d=s.split("-");
		}

		int year=Integer.valueOf(d[0]);
		int month=Integer.valueOf(d[1]);

		Calendar cal = Calendar.getInstance();
		
		
		
		cal.set(year,month,1);       //Calendar에서는 1월이 0이므로 우리가 사용하는 월에서 -1 해줘야 합니다.
		return cal.getActualMaximum(Calendar.DATE);

		
	}
	

	
	public static String date2String(Date d){
		String str="";
		if(d.getMonth()+1<10){
			if(d.getDate()<10){
				str = (d.getYear()-100)+"/"+"0"+(d.getMonth()+1)+"/"+"0"+d.getDate();
			}
			else{
				str = (d.getYear()-100)+"/"+"0"+(d.getMonth()+1)+"/"+d.getDate();
			}
		}
		else{
			if(d.getDate()<10){
				str = (d.getYear()-100)+"/"+(d.getMonth()+1)+"/"+d.getDate();
			}
			else{
				str = (d.getYear()-100)+"/"+(d.getMonth()+1)+"/"+"0"+d.getDate();
			}

		}

		return str;
	}

}
