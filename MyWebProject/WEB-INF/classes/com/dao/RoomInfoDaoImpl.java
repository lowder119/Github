package com.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.RoomInfo;
@Repository
public class RoomInfoDaoImpl implements RoomInfoDao{
	
	@Autowired
	private SqlSessionFactory factory;
	
	public List<RoomInfo> getRoomInfo(String location){
		List<RoomInfo> roomInfo = factory.openSession().selectList("roomInfo.getRoomInfo", location);
		return roomInfo;
	}
}
