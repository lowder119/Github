package com.dao;

import static com.common.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.entity.LoginVO;

//		CRUD �޼ҵ�
public class LoginDAO {
	Connection conn = null;
	
	public LoginDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<LoginVO> getSelectAllUser() {
		// DB���� ���� �� �о ǥ�� �����ϴ� ResultSet�� ���� List��ü�� ���� �� ����
		Statement stmt = null;
		ResultSet rs = null;
		LoginVO te = null;
		List<LoginVO> res = new ArrayList<>();
		String sql = "select * from login";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while ( rs.next() ) {
				te = new LoginVO(rs.getString(1), rs.getString(2));
				res.add(te);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Close(rs);
			Close(stmt);
			Close(conn);
		}
		
		return res;
	}
	
	public int insertLoginEntity(LoginVO t) {
		PreparedStatement pstmt = null;
		String sql = "insert into login values(?, ?)";
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getId());
			pstmt.setString(2, t.getPassWord());
			res = pstmt.executeUpdate(); 
			
			if ( res > 0 ) 
				Commit(conn);
			
		} catch (SQLException e) {
			Rollback(conn);
			e.printStackTrace();
		} finally {
			Close(pstmt);
			Close(conn);
		}		
		
		return res;
	}
	
	public int deleteLoginEntity(LoginVO t) {
		PreparedStatement pstmt = null;
		String sql = "delete from login where id = ?";
		int res = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getId());
			res = pstmt.executeUpdate(); 
			
			if ( res > 0 ) 
				Commit(conn);
			
		} catch (SQLException e) {
			Rollback(conn);
			e.printStackTrace();
		} finally {
			Close(pstmt);
			Close(conn);
		}
		
		return res;
	}
}