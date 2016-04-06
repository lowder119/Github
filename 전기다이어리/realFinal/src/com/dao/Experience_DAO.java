package com.dao;

import static com.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.entity.ApplianceVO;

public class Experience_DAO {
	Connection conn;
	static int index = 0;
	static Map<Integer, ApplianceVO> list = new HashMap<>();

	public Experience_DAO(Connection conn) {
		this.conn = conn;
	}

	public Map<Integer, ApplianceVO> get_listAppliance(String Item, String Item_Input) {
		Statement stmt = null;
		ResultSet re = null;

		String sql = "Select * from Appliance where Item_Name ='" + Item + "' ";

		try {
			stmt = conn.createStatement();
			re = stmt.executeQuery(sql);

			ApplianceVO appEntity = new ApplianceVO();

			re.next();
			appEntity.setItemNo(re.getInt("ItemNo"));
			appEntity.setItem_Name(Item_Input);
			appEntity.setPower_Consumption(re.getInt("Power_Consumption"));
			appEntity.setMonth_Consume_Power(appEntity.getPower_Consumption());

			list.put(index, appEntity);
			index++;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(re);
			Close(stmt);
		}
		return list;
	}
	

	public void MapClear() {
		list.clear();
		index = 0;
		setMyApplianceDelete();
	}

	///// 수정//////////
	public Map<Integer, ApplianceVO> getMyAppliance(String myID){
		Statement stmt =null;
		ResultSet re = null;
		String sql = "select * from myAppliance where id = '" + myID+"'";
		try{
			stmt =  conn.createStatement();
			re = stmt.executeQuery(sql);
			while(re.next()){
				ApplianceVO vo = new ApplianceVO();
				vo.setItemNo(re.getInt("ITEMNO"));
				vo.setItem_Name(re.getString("ITEM_NAME"));
				vo.setPower_Consumption(re.getInt("POWER_CONSUMPTION"));
				vo.setNum(re.getInt("NUM"));
				vo.setHour(re.getInt("HOUR"));
				vo.setPeriod(re.getString("PERIOD"));
				vo.setMonth_Consume_Hour(re.getInt("MONTH_CONSUME_HOUR"));
				vo.setMonth_Consume_Power(re.getInt("POWER_CONSUMPTION"));
				
				list.put(index, vo);
				index++;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			Close(re);
			Close(stmt);
		}
		return list;
	}
	
	public void setMyAppliance(Map<Integer,ApplianceVO> map , int index, String myID){
		PreparedStatement pstmt = null;
		setMyApplianceDelete();
		String sql = "insert into MyAppliance values(?,?,?,?,?,?,?,?,?)";
		int n=0;
		try{
			for(int i=0; i<index ; i++){
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, map.get(i).getItemNo());
				pstmt.setString(2, map.get(i).getItem_Name());
				pstmt.setInt(3, map.get(i).getPower_Consumption());
				pstmt.setInt(4, map.get(i).getNum());
				pstmt.setInt(5, map.get(i).getHour());
				pstmt.setString(6, map.get(i).getPeriod());
				pstmt.setInt(7, map.get(i).getMonth_Consume_Hour());
				pstmt.setDouble(8, map.get(i).getMonth_Consume_Power());
				pstmt.setString(9, myID);
				
				n = pstmt.executeUpdate();
				if(n>0){
					Commit(conn);
				}
			}
			//System.out.println("저장완료");
		}catch(SQLException e){
			Rollback(conn);
			e.printStackTrace();
		}finally{
			Close(pstmt);
		}
	}
	
	public void setMyApplianceDelete(){
		PreparedStatement pstmt = null;
		String sql = "delete from MyAppliance";
		int n=0;
		try{
		pstmt = conn.prepareStatement(sql);
		n = pstmt.executeUpdate();
		if(n>0){
			Commit(conn);
		}
		}catch(SQLException e){
			Rollback(conn);
			e.printStackTrace();
		}finally{
			Close(pstmt);
		}
	}
}
