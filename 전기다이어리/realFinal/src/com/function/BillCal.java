package com.function;

import javax.swing.JLabel;

public class BillCal {
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9;
	
	
	public BillCal(JLabel l1, JLabel l2, JLabel l3, JLabel l4, JLabel l5, JLabel l6, JLabel l7,JLabel l8,JLabel l9) {
	
		this.l1 = l1;
		this.l2 = l2;
		this.l3 = l3;
		this.l4 = l4;
		this.l5 = l5;
		this.l6 = l6;
		this.l7 = l7;
		this.l8 = l8;
		this.l9 = l9;
		
	}
	public void setMonth(String y,String m){
		l9.setText(y);
		l8.setText(m);
	}

	public void set0(){
		l1.setText("");
		l2.setText("");
		l3.setText("");
		l4.setText("");
		l5.setText("");
		l6.setText("");
		l7.setText("");
	}
}
