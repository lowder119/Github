package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.biz.NotifyInfoBiz;
import com.biz.StudentInfoBiz;
import com.entity.NotifyInfo;
import com.entity.StudentInfo;
import com.validate.StudentInfoValidate;

@Controller
public class MyController {
	
	@Autowired
	StudentInfoBiz studentInfoBiz;
	@Autowired
	NotifyInfoBiz notifyInfoBiz;
	
	@RequestMapping("main.do")
	public ModelAndView getHeader(){
		ModelAndView mav = new ModelAndView("/main");
		return mav;
	}
	@RequestMapping("footer.do")
	public ModelAndView getFooter(){
		ModelAndView mav = new ModelAndView("/footer");
		return mav;
	}
	@RequestMapping("home.do")
	public ModelAndView goHome(){
		ModelAndView mav = new ModelAndView("/main_home");
		return mav;
	}	
	
	@RequestMapping(value="mainpage.do",method=RequestMethod.POST)
	public ModelAndView getStudentInfo(@RequestParam int studentCode,
								@RequestParam String pw,HttpSession session){
		ModelAndView mav = new ModelAndView();
		StudentInfo studentInfo = new StudentInfo();
		studentInfo.setStudentCode(studentCode);
		StudentInfo studentInfo2 = studentInfoBiz.getStudentInfo(studentInfo); 
		//DB에 없는 학번을 입력하면 studentInfo2에는 null이 들어오고 studentInfo2를 사용하게되면 nullPointException 발생
		try {
			if (studentInfo2.getStudentCode() == studentCode && studentInfo2.getPw().equals(pw)) {
				session.setAttribute("studentinfo", studentInfo2);
				mav = getNotifyInfo(session);
			} else {
				mav.setViewName("redirect:/Login.jsp");
				mav.addObject("errCode",2); //비밀번호 다름
			}
		} catch (NullPointerException e) {
			mav.setViewName("redirect:/Login.jsp");
			mav.addObject("errCode",3); //디비에 없음
			return mav;
		}
		return mav;
	}
	
	@RequestMapping("getNotifyInfo.do")
	public ModelAndView getNotifyInfo(HttpSession session){
		List<NotifyInfo> list = notifyInfoBiz.getNotifyInfo();
		session.setAttribute("NotifyInfoList",list);
		ModelAndView mav = new ModelAndView("/main_home");
		return mav;
	}
}
