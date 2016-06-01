package com.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.RentalRoom;

@Repository
public class RentalRoomDaoImpl implements RentalRoomDao{
	
	@Autowired
	private SqlSessionFactory factory;
	
	public List<RentalRoom> getRentalRoom(String rentalDate){
		List<RentalRoom> rentalRoomList = factory.openSession().selectList("rentalRoom.getRentalRoomInfo", rentalDate);
		return rentalRoomList;
	}
	
	/*public int insertRentalRoom(RentalRoom rentalroom){
		String sql = "insert into rentalroom values(to_date(?,'MM/DD/YYYY'), ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		int result=0;
		
		try{
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rentalroom.getRental_date());
			pstmt.setString(2, rentalroom.getRental_time());
			pstmt.setString(3, rentalroom.getRoomcode());
			pstmt.setInt(4, rentalroom.getStudentcode());
			pstmt.setInt(5, rentalroom.getPersons());
			pstmt.setString(6, rentalroom.getStudentcodes());
			pstmt.setString(7, rentalroom.getReason());
			
			result = pstmt.executeUpdate();
			if(result>0){
				Commit(con);
			}
			
		}catch(Exception e){
			Rollback(con);
			System.out.println(e);
		}finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}
*/}
