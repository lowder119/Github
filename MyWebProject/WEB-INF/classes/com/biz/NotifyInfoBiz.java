package com.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.NotifyInfoDao;
import com.entity.NotifyInfo;


@Service
public class NotifyInfoBiz {
	
	@Autowired
	private NotifyInfoDao notifyInfoDao;
	
	public List<NotifyInfo> getNotifyInfo(){
		return notifyInfoDao.getNotifyInfo();
	}
}
