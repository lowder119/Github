package com.dao;

import java.util.List;

import com.entity.RentalRoom;

public interface RentalRoomDao {
	public List<RentalRoom> getRentalRoom(String rentalDate);
}
