package com.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.biz.RentalRoomBiz;
import com.biz.RoomInfoBiz;
import com.entity.RentalRoom;
import com.entity.RoomInfo;

@Controller
public class RentalSpaceController {
	
	@Autowired
	private RoomInfoBiz roomInfoBiz;
	@Autowired
	private RentalRoomBiz rentalRoomBiz;
	
	@RequestMapping("rentalSpace.do")
	public ModelAndView goRentalSpace(){
		ModelAndView mav = new ModelAndView("/rentalSpace");
		return mav;
	}
	
	@RequestMapping("choice.do")
	public ModelAndView goRentalSpace2(@RequestParam String location,
			@RequestParam String choice_date, HttpSession session){
		
		List<RoomInfo> roominfoList = roomInfoBiz.getRoomInfo(location);
		List<RentalRoom> rentalroomList = rentalRoomBiz.getRentalRoom(choice_date);
		
		session.setAttribute("roominfoList", roominfoList);
		session.setAttribute("rentalroomList", rentalroomList);
		session.setAttribute("choice_date", choice_date);
		
		ModelAndView mav = new ModelAndView("rentalSpace2");
		return mav;
	}
}
