package com.biz;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RoomInfoDao;
import com.entity.RoomInfo;

@Service
public class RoomInfoBiz {
	
	@Autowired
	private RoomInfoDao roomInfoDao;
	
	public List<RoomInfo> getRoomInfo(String location){
		String location2 = location+"%";
		return roomInfoDao.getRoomInfo(location2);
	}
}
