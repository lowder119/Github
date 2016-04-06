package com.common;
//DB����, DB����,�������,sql���� commit,sql��� rollback
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//new �����ڷ� ���� �������ʰ�, static���� �ִ� db�� �����پ��� ����.
public class JDBCTemplate {

	//�����ڵ�
	public static Connection getConnection(){
		Connection con = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="maven";
			String password="admin";
			con = DriverManager.getConnection(url,user,password);
			con.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}		
		return con;
	}

	public static boolean isConnection(Connection conn){
		boolean valid = true;
		try {
			if(conn==null || conn.isClosed()){
				valid=false;
			}
		} catch (SQLException e) {
			valid=true;
			e.printStackTrace();
		}
		return valid;
	}

	//DB����
	public static void Close(Connection conn){
		if(isConnection(conn)){
			try{
				conn.close();
			}catch(SQLException e ) {
				e.printStackTrace();
			}
		}
	}
	//Statement ����
	public static void Close(Statement stmt){
		try {
			if(stmt!=null){
				stmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//ResultSet ����
	public static void Close(ResultSet rs ){
		try {
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Ʈ�����ó��(commit)----------------------------------------
	public static void Commit(Connection conn){
		
		if(isConnection(conn)){
			try {
				conn.commit();	
				System.out.println("commit ok");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
	}
	//Ʈ�����ó��(rollback)----------------------------------------
	public static void Rollback(Connection conn){
		if(isConnection(conn)){
			try {
				conn.rollback();
				System.out.println("rollback ok");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}


