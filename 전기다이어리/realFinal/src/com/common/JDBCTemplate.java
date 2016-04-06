package com.common;
//DB연결, DB해제,명령해제,sql저장 commit,sql취소 rollback
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//new 연산자로 새로 만들어내지않고, static으로 있는 db를 가져다쓰며 공유.
public class JDBCTemplate {

	//연결코드
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

	//DB해제
	public static void Close(Connection conn){
		if(isConnection(conn)){
			try{
				conn.close();
			}catch(SQLException e ) {
				e.printStackTrace();
			}
		}
	}
	//Statement 해제
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
	
	//ResultSet 해제
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
	//트랜잭션처리(commit)----------------------------------------
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
	//트랜잭션처리(rollback)----------------------------------------
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


