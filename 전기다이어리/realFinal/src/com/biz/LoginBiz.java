package com.biz;

import static com.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.dao.LoginDAO;
import com.entity.LoginVO;

//		View -> controller -> biz -> dao
public class LoginBiz {
	
	public List<LoginVO> getSelectAllUser() {
		Connection conn = getConnection();
		List<LoginVO> list = new LoginDAO(conn).getSelectAllUser();
		Close(conn);
		
		return list;
	}
	public int insertLoginEntity(LoginVO vo) {
		Connection conn = getConnection();
		int n = new LoginDAO(conn).insertLoginEntity(vo);
		Close(conn);
		
		return n;
	}
	
	public int deleteLoginEntity(LoginVO vo) {
		Connection conn = getConnection();		
		int cnt = new LoginDAO(conn).deleteLoginEntity(vo);
		Close(conn);
		
		return cnt;
	}	
}
