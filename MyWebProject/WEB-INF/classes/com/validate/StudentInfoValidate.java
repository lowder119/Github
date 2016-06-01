package com.validate;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.entity.StudentInfo;

public class StudentInfoValidate implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return StudentInfo.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors error) {
		 StudentInfo studentInfo = (StudentInfo) target;
		
		 if(studentInfo.getStudentCode() < 10){
			 System.out.println("학번:10자리 미만");
			 error.reject("studentCode");
		 }
		 
/*		 if(studentInfo.getPw()==null || studentInfo.getPw().trim().isEmpty()){
			 System.out.println("패스워드 입력 필요");
			 error.reject("pw");
		 }*/
	}

}
