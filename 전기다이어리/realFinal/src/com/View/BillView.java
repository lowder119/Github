package com.View;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;


import com.biz.Progressive_Biz;
import com.entity.ProgressiveVO;
import javax.swing.JTextField;

public class BillView {
	private String r1 = "저압(주택)";
	private static int m,y; //날짜
	private JLabel kWh_label, power_label, VATlabel;
	private JLabel lblBasicprice, lblPowerprice,  lblTotal;
	private JLabel lblBase, lblPower, sumBPlabel;
	private JLabel powerTaxlabel, totalPricelabel;
	private JLabel sumBPvalue, lVat_value, Tax_value;
	private int Power_Charge;
	private String Power_Charge2 = null;
	private JTextField textField;
	
	
	public BillView(){
///////////////////월별 사용량 합치기////////////////
		Calendar today = Calendar.getInstance();
		int y = today.get(Calendar.YEAR);
		int nm = today.get(Calendar.MONTH)+1;
		

		JFrame jf = new JFrame("전기요금내역 상세");
		jf.setSize(450, 450);
		jf.getContentPane().setLayout(null);
		
		Progressive_Biz ProBiz3= new Progressive_Biz();
		
		JPanel bill_panel = new JPanel();
		bill_panel.setLocation(0, 0);
		bill_panel.setSize(434, 401);
		bill_panel.setLayout(null);
		jf.getContentPane().add(bill_panel);
		
		JButton button = new JButton("상세요금");
		button.setBounds(300, 10, 97, 23);
		bill_panel.add(button);
		
		JLabel year_value = new JLabel("Y");
		year_value.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		year_value.setBounds(30, 14, 57, 15);
		bill_panel.add(year_value);
		year_value.setText(String.valueOf(y));	
		
		JLabel year_label = new JLabel("\uB144");
		year_label.setBounds(87, 14, 19, 19);
		bill_panel.add(year_label);
		
		JLabel month_value = new JLabel("M");
		month_value.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		month_value.setBounds(137, 14, 34, 15);
		bill_panel.add(month_value);
		month_value.setText(String.valueOf(nm));		
		
		JLabel label = new JLabel("\uC6D4");
		label.setBounds(185, 14, 18, 19);
		bill_panel.add(label);
		
		textField = new JTextField("");
		textField.setBounds(213, 63, 116, 21);
		bill_panel.add(textField);
		
		textField.setColumns(10);
		
		
		kWh_label = new JLabel("kwh");
		kWh_label.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		kWh_label.setBounds(357, 49, 57, 41);
		bill_panel.add(kWh_label);
		
		JLabel price_value = new JLabel("price");
		price_value.setHorizontalAlignment(SwingConstants.RIGHT);
		price_value.setFont(new Font("맑은 고딕", Font.BOLD, 26));
		price_value.setForeground(Color.MAGENTA);
		price_value.setBounds(89, 335, 200, 36);
		bill_panel.add(price_value);
		
		JLabel won_label = new JLabel("won");
		won_label.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		won_label.setBounds(357, 344, 57, 23);
		bill_panel.add(won_label);	 
		
		lblBasicprice = new JLabel("기본요금(원미만 절사)");
		lblBasicprice.setBounds(40, 100, 135, 15);
		bill_panel.add(lblBasicprice);
		
		lblBase = new JLabel("base");
		lblBase.setBounds(347, 100, 50, 15);
		lblBase.setHorizontalAlignment(SwingConstants.RIGHT);
		bill_panel.add(lblBase);
		
		lblPowerprice = new JLabel("전력량요금(원미만 절사)");
		lblPowerprice.setBounds(40, 130, 140, 15);
		bill_panel.add(lblPowerprice);
		
		lblPower = new JLabel("power");
		bill_panel.add(lblPower);
		lblPower.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPower.setBounds(347, 130, 50, 15);
		bill_panel.add(lblPower);
		
		sumBPlabel = new JLabel("합계(기본요금 + 전력량요금)");
		sumBPlabel.setBounds(40, 160, 200, 15);
		bill_panel.add(sumBPlabel);
		
		sumBPvalue = new JLabel("sumbp");
		sumBPvalue.setForeground(Color.BLUE);
		sumBPvalue.setHorizontalAlignment(SwingConstants.RIGHT);
		sumBPvalue.setBounds(347, 160, 50, 15);
		bill_panel.add(sumBPvalue);
		
		VATlabel = new JLabel("부가가치세(원미만 4사 5입)");
		VATlabel.setBounds(40, 190, 178, 15);
		bill_panel.add(VATlabel);
		
		lVat_value = new JLabel("vat");
		lVat_value.setHorizontalAlignment(SwingConstants.RIGHT);
		lVat_value.setBounds(347, 190, 50, 15);
		bill_panel.add(lVat_value);
		
		powerTaxlabel = new JLabel("전력산업기반기금(10원미만 절사)");
		powerTaxlabel.setBounds(40, 220, 194, 15);
		bill_panel.add(powerTaxlabel);
		
		Tax_value = new JLabel("tax");
		Tax_value.setHorizontalAlignment(SwingConstants.RIGHT);
		Tax_value.setBounds(347, 220, 50, 15);
		bill_panel.add(Tax_value);
		
		totalPricelabel = new JLabel("합계 + 부가가치세 + 전력산업기반금 = 청구금액");
		totalPricelabel.setBounds(39, 256, 337, 15);
		bill_panel.add(totalPricelabel);
		
		lblTotal = new JLabel("total");
		lblTotal.setFont(new Font("굴림", Font.BOLD, 12));
		lblTotal.setBounds(40, 280, 337, 15);
		lblTotal.setBackground(Color.green);
		bill_panel.add(lblTotal);
		
		
		//사용량 데이터 받아오기
		button.addActionListener(new ActionListener() {	
		@Override
		public void actionPerformed(ActionEvent e) {
			double power = Double.parseDouble(textField.getText());
			int power2 = (int)power;
//			System.out.println(m);
			
			textField.setText(String.valueOf(power));
			Progressive_Biz ProBiz2= new Progressive_Biz();
	
			Power_Charge = ProBiz2.CalculateProgressive(power2, r1);
			Power_Charge2 = Power_Charge + "원";
			price_value.setText(Power_Charge2);
						
			List<ProgressiveVO> list = ProBiz3.getProgressiveList();
		    int basic_rate = ProBiz3.getBasic_Rate(power2,list);
//		    System.out.println("기본요금"+power);
		    lblBase.setText(String.valueOf(basic_rate + "원"));
			
			
			int use_price =ProBiz3.getDetail_Progressive1(power2, list);
			lblPower.setText(String.valueOf(use_price + "원"));
			
			
			int sumBP = (basic_rate + use_price);
			sumBPvalue.setText(String.valueOf(sumBP + "원"));
			
			
			double vat =  sumBP * 0.1;
			lVat_value.setText(String.valueOf(Math.round(vat)) +"원");
			
			
			double tax =  sumBP * 0.037;
			Tax_value.setText(String.valueOf(Math.round(tax)) +"원");
			
		
			lblTotal.setText(sumBP + "+" + Math.round(vat)+ "+" + Math.round(tax)+ "="  + Power_Charge2);
		}
		} );
	
		jf.setLocation(400, 200);
		jf.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		BillView m = new BillView();
	}

}
