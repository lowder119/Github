package com.biz;

import static com.common.JDBCTemplate.*;

import java.awt.event.ItemEvent;
import java.sql.Connection;

import javax.swing.JCheckBox;

import com.dao.Experience_DAO;
import com.entity.ApplianceVO;

import java.util.Map;

public class Experience_Biz {
	static int table_index=0;
	static Map<Integer,ApplianceVO> list;

	
	public Experience_Biz(){
	}
	
	public Map<Integer,ApplianceVO> get_listAppliance(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String Item_Input = ((JCheckBox) e.getItem()).getText();
			String Item = null;
			switch(Item_Input){
			case "TV": 
				Item = "TV";
				break;
			case "������":
				Item = "Aircondition";
				break;
			case "�����":
				Item = "audio";
				break;
			case "��ǳ��":
				Item = "fan";
				break;
			case "������":
				Item = "fluorescentLight";
				break;
			case "����û����":
				Item = "air_cleaner";
				break;
			case "������":
				Item = "humidifier";
				break;
			case "�����":
				Item = "refrigerator";
				break;
			case "������(���)":
				Item = "Cooker";
				break;
			case "���ڷ�����":
				Item = "microwave";
				break;
			case "�ͼ���":
				Item = "blender";
				break;
			case "��ǻ��":
				Item = "computer";
				break;
			case "�����":
				Item = "monitor";
				break;
			case "������":
				Item = "printer";
				break;
			case "�޴���������":
				Item = "cellphoneCharger";
				break;
			case "DVD Player":
				Item = "DVDplayer";
				break;
			case "��ġ�����":
				Item = "kimchiFridge";
				break;
			case "��Ź��":
				Item = "washingMachine";
				break;
			case "����û�ұ�":
				Item = "vacuum";
				break;
			case "������̱�":
				Item = "HairDryer";
				break;
			case "��":
				Item = "bidet";
				break;
			}
			//System.out.println(Item + "�� �����߽��ϴ�.");
			
			Connection conn = getConnection();
			list = new Experience_DAO(conn).get_listAppliance(Item,Item_Input);
			Close(conn);
			return list;

		} else {
			//System.out.println(" �����߽��ϴ�.");
			return null;
		}
	}
	
	public String[] getRow(int index){
		String[] row = {list.get(index).getItem_Name(), String.valueOf(list.get(index).getPower_Consumption()), String.valueOf(list.get(index).getNum()), String.valueOf(list.get(index).getHour())
				, list.get(index).getPeriod(), String.valueOf(list.get(index).getMonth_Consume_Hour()), String.valueOf(list.get(index).getMonth_Consume_Power())};
		return row;
	}
	
	public Map<Integer, ApplianceVO> Changed_Content(String Changed_Text, int col, Map<Integer,ApplianceVO> map, int row){
		ApplianceVO entity= (ApplianceVO)map.get(row);
		switch(col){
		case 1:
			entity.setPower_Consumption(Integer.parseInt(Changed_Text));
			entity.setMonth_Consume_Power(Integer.parseInt(Changed_Text));
			break;
		case 2:
			entity.setNum((Integer.parseInt(Changed_Text)));
			entity.setMonth_Consume_Hour(Integer.parseInt(Changed_Text)*entity.getMonth_Consume_Hour());
			entity.setMonth_Consume_Power(entity.getPower_Consumption());
			break;
		case 3:
			entity.setHour(Integer.parseInt(Changed_Text));
			entity.setMonth_Consume_Hour(Integer.parseInt(Changed_Text)*entity.getMonth_Consume_Hour());
			entity.setMonth_Consume_Power(entity.getPower_Consumption());
			break;
		}
		list.replace(row, entity);
		return list;
	}
	
	public void MapClear(){
		Connection conn = getConnection();
		new Experience_DAO(conn).MapClear();
		Close(conn);
		list.clear();
		table_index=0;
	}
	
	/////����//////////
	public Map<Integer, ApplianceVO> getMyAppliance(String myID){
		Connection conn = getConnection();
		list = new Experience_DAO(conn).getMyAppliance(myID);
		Close(conn);
		//table_index = list.size() -1;
		return list;
	}
	
	public void setMyAppliance(Map<Integer,ApplianceVO> map , int index, String myID){
		Connection conn = getConnection();
		new Experience_DAO(conn).setMyAppliance(map, index, myID);
		Close(conn);
	}
}
