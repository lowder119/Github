package com.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.NotifyInfo;


@Repository
public class NotifyInfoDaoImpl implements NotifyInfoDao {
	
	@Autowired
	private SqlSessionFactory factory;
	
	public List<NotifyInfo> getNotifyInfo(){
		List<NotifyInfo> list = factory.openSession().selectList("notifyInfo.getNotifyList");
		return list;
	}
}
