package com.dao;

import static com.common.JDBCTemplate.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.ProgressiveVO;

public class Progressive_DAO{
	Connection conn; 
	static List<ProgressiveVO> list; 
	
	public Progressive_DAO(Connection conn) {
		this.conn = conn;
	}
	
	public int CalculateProgressive(int Power, String button) {
		Statement stmt = null;
		ResultSet re = null;
		double sum =0;
		int power2 = Power;
		String sql = null;
		list = new ArrayList<>();

		if(button.equals("저압(주택)")){
			sql = "Select * from progressive_low where grade<=(select grade from Progressive_low where "
					+ Power +" between lowpower and highpower)";
		}
		else if(button.equals("고압(주택)")){
			sql = "Select * from progressive_high where grade<=(select grade from Progressive_high where "
					+ Power +" between lowpower and highpower)";
		}

		try {
			stmt = conn.createStatement();
			re = stmt.executeQuery(sql);
			while(re.next()){
				ProgressiveVO vo = new ProgressiveVO();
				vo.setGrade(re.getInt("grade"));
				vo.setLowpower(re.getInt("lowpower"));
				vo.setHighpower(re.getInt("highpower"));
				vo.setBasic_rate(re.getInt("basic_rate"));
				vo.setAdditional_rate(re.getDouble("additional_rate"));
				list.add(vo);
	
				if( (power2 > 100) &&(re.getInt("grade")< 6)){
				sum += re.getDouble("additional_rate")* 100;
				power2 -= 100;
				}
				else if((power2 >100) && (re.getInt("grade") ==6)){
					//System.out.println("옵니다.");
					sum += re.getDouble("additional_rate")*power2+re.getInt("basic_rate");
				}
				else {
					sum += re.getDouble("additional_rate")*power2+re.getInt("basic_rate"); 
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Close(re);
			Close(stmt);
		}
		sum = Math.floor(sum);
		int usded_charge = (int)sum;
		//System.out.println(usded_charge);
		//System.out.println();
		usded_charge =  usded_charge + (int)((((int)(usded_charge*0.037))/10*10)+ Math.round(usded_charge*0.1));
		usded_charge = usded_charge/10*10; //10원미만 절사
		return usded_charge;
	}
	
	public List<ProgressiveVO> getProgressiveList(){
		return list;
	}
}
