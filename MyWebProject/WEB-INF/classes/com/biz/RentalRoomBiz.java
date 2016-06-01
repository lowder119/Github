package com.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.RentalRoomDao;
import com.entity.RentalRoom;
@Service
public class RentalRoomBiz {
	
	@Autowired
	private RentalRoomDao rentalRoomdao;
	
	public List<RentalRoom> getRentalRoom(String rentalDate){
		return rentalRoomdao.getRentalRoom(rentalDate);
	}
	
	/*public int insertRentalRoom(RentalRoom rentalroom){
		Connection con =getConnection();
		int result = new RentalRoomDao(con).insertRentalRoom(rentalroom);
		Close(con);
		return result;
	}*/
}
