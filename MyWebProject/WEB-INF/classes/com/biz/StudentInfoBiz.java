package com.biz;

import com.dao.StudentInfoDao;
import com.entity.StudentInfo;
import static com.common.JDBCTemplate.*;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StudentInfoBiz implements StudentInfoDao{
	
	@Autowired
	private SqlSessionFactory factory;
	
	public StudentInfo getStudentInfo(StudentInfo studentinfo){
		return factory.openSession().selectOne("getStudentInfo", studentinfo);
	}
}