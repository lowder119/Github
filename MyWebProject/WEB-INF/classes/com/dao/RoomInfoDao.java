package com.dao;

import java.util.List;
import com.entity.RoomInfo;

public interface RoomInfoDao {
	public List<RoomInfo> getRoomInfo(String location);
}
