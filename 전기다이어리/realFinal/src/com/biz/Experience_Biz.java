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
			case "에어컨":
				Item = "Aircondition";
				break;
			case "오디오":
				Item = "audio";
				break;
			case "선풍기":
				Item = "fan";
				break;
			case "형광등":
				Item = "fluorescentLight";
				break;
			case "공기청정기":
				Item = "air_cleaner";
				break;
			case "가습기":
				Item = "humidifier";
				break;
			case "냉장고":
				Item = "refrigerator";
				break;
			case "전기밥솥(취사)":
				Item = "Cooker";
				break;
			case "전자레인지":
				Item = "microwave";
				break;
			case "믹서기":
				Item = "blender";
				break;
			case "컴퓨터":
				Item = "computer";
				break;
			case "모니터":
				Item = "monitor";
				break;
			case "프린터":
				Item = "printer";
				break;
			case "휴대폰충전기":
				Item = "cellphoneCharger";
				break;
			case "DVD Player":
				Item = "DVDplayer";
				break;
			case "김치냉장고":
				Item = "kimchiFridge";
				break;
			case "세탁기":
				Item = "washingMachine";
				break;
			case "진공청소기":
				Item = "vacuum";
				break;
			case "헤어드라이기":
				Item = "HairDryer";
				break;
			case "비데":
				Item = "bidet";
				break;
			}
			//System.out.println(Item + "를 선택했습니다.");
			
			Connection conn = getConnection();
			list = new Experience_DAO(conn).get_listAppliance(Item,Item_Input);
			Close(conn);
			return list;

		} else {
			//System.out.println(" 비선택했습니다.");
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
	
	/////수정//////////
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
