package com.function;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;

import com.entity.ElectricVo;

public class DayDeco {
	
	
	public ArrayList<JButton> DayCheck(Component[] c, ArrayList<ElectricVo> g){
		//c는 모든 날짜버튼이 들어있는 컴포넌트 배열. g는 db에 입력돼있는 해당 월의 정보가 들어있는 리스트.
		ArrayList<String> days = new ArrayList<>();
		ArrayList<JButton> r = new ArrayList<>();
		
		if(g==null){
			for(Component comp : c){
				r.add((JButton)comp);
			}
			return r;
		}
		
		
		for(ElectricVo v : g){
			days.add(Integer.toString(v.getD().getDate()));
		}
		
		for(int i=0;i<c.length;i++){
			if(days.contains(((JButton)c[i]).getText()))
				r.add((JButton)c[i]);
		}
		return r;
	}
	public void DayBackgroundDeco(ArrayList<JButton> j,Color c){
		for(JButton b:j){
			b.setBackground(c);
		}		
	}
	public void DayFontDeco(ArrayList<JButton> j,Font f){
		for(JButton b:j){
			b.setFont(f);
		}		
	}
	public void DayTextDeco(ArrayList<JButton> j,String k,String FrontOrBehind){
		if(FrontOrBehind =="Front"){
			for(JButton b:j){
				b.setText(k+b.getText());
			}		
		}else{
			for(JButton b:j){
				b.setText(b.getText()+k);
			}		
		}

	}



}
