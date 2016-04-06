package com.dao;
import static com.common.JDBCTemplate.Close;
import static com.common.JDBCTemplate.Commit;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.ElectricVo;
public class ElectricDAO {
	Connection con = null;
	public ElectricDAO(Connection con){
		this. con = con;
	}

	public List<ElectricVo> selectSQL(String s){//select
		Statement stmt = null;
		ResultSet rs = null;
		ElectricVo ev =null; 
		List<ElectricVo> res = new ArrayList<>();
		String sql = s;
		try{
			stmt = con.createStatement();
			rs=stmt.executeQuery(sql);
			while(rs.next()){
				ev = new ElectricVo();
				ev.setId(rs.getString(1));
				ev.setD(rs.getDate(2));
				ev.setUsed(rs.getDouble(3));
				res.add(ev);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			Close(rs);
			Close(stmt);
			Close(con);
		}
		return res;
	}

	public int insertVo(ElectricVo vo){
		PreparedStatement pstm = null;

		int res=0;
		String sql = "INSERT INTO elec VALUES('"+vo.getId()+"','"+ElectricVo.date2String(vo.getD())+"',"+vo.getUsed()+")";
		//System.out.println("INSERT INTO elec VALUES('"+vo.getId()+"','"+ElectricVo.date2String(vo.getD())+"',"+vo.getUsed()+")");
		try {
			pstm=con.prepareStatement(sql);

			res = pstm.executeUpdate();

			if(res>0){
				Commit(con);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
//			Rollback(con);
			Close(pstm);


		}
		return res;		

	}

	public int replaceVo(String date,double use){
		PreparedStatement pstm = null;
		int res=0;
		String sql = "update elec set use="+use+" where day like '"+date+"'";
		
		//String sql = "delete from elec where day '"+date+"' and use ='"+use+"'";
		try {
			pstm=con.prepareStatement(sql);



			res = pstm.executeUpdate();

			if(res>0){
				Commit(con);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
//			Rollback(con);
			Close(pstm);


		}
		return res;

	}
	
	public int deleteVo(String date,String id){
		
		PreparedStatement pstm = null;
		int res=0;
		String sql = "delete from elec where ID = '"+id+"' and day like '"+date+"'";
		try {
			pstm=con.prepareStatement(sql);
						res = pstm.executeUpdate();

			if(res>0){
				Commit(con);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//System.out.println(e);
		}finally{
//			Rollback(con);
			Close(pstm);


		}
		return res;

		
	}
	
	

}
