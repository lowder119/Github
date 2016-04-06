package com.biz;
import static com.common.JDBCTemplate.Close;
import static com.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.dao.ElectricDAO;
import com.entity.ElectricVo;

public class ElectricBiz {
	public ElectricBiz(){	}
	
	public List<ElectricVo> selectSQL(String s){//select
		Connection conn = getConnection();
		List<ElectricVo> list = new ElectricDAO(conn).selectSQL(s);
		Close(conn);
		return list;
	}
	public int insertVo(ElectricVo vo){
		Connection conn = getConnection();
		int r =new ElectricDAO(conn).insertVo(vo);
		Close(conn);
		return r;
	}
	
	public int replaceVo(String date,double use){
		Connection conn = getConnection();
		int r = new ElectricDAO(conn).replaceVo(date, use);
		Close(conn);
		return r;
	}
	
	public int deleteVo(String date,String id){
		Connection conn = getConnection();
		int r = new ElectricDAO(conn).deleteVo(date, id);
		Close(conn);
		return r;
	}
}
