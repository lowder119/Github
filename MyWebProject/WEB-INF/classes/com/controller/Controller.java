package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.biz.RentalRoomBiz;
import com.biz.RoomInfoBiz;
import com.biz.StudentInfoBiz;
import com.entity.*;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/mainpage","/choice","/doGoRentalRoomForm","/rentalroom","/notify_attention","/notify_attention_content"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request,response);
	}

	private void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		if(uri.endsWith("mainpage")){
			doLogin(request,response);
		}else if(uri.endsWith("choice")){
			doGetRoomInfo(request,response);
		}else if(uri.endsWith("doGoRentalRoomForm")){
			doGoRentalRoomForm(request,response);
		}else if(uri.endsWith("rentalroom")){
			doRentalroom(request,response);
		}else if(uri.endsWith("notify_attention")){
			doGetnotify_attention(request,response);
		}else if(uri.endsWith("notify_attention_content")){
			doGoNotify_Content(request,response);
		}
	}

	private void doGoNotify_Content(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
/*		HttpSession session = request.getSession();
		int writing_number = Integer.parseInt(request.getParameter("writing_number"));
		Notify_Attention notify = new Notify_AttentionBiz().getNotify_Attention(writing_number);
		session.setAttribute("notifyAttention", notify);
		response.sendRedirect("notify_attention_content.jsp");*/
	}

	private void doGetnotify_attention(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	/*	HttpSession session = request.getSession();
		List<Notify_Attention> list = new Notify_AttentionBiz().getNotify_Attention();
		session.setAttribute("notifyAttention_list", list);
		response.sendRedirect("notify_attention.jsp");*/
	}

	private void doRentalroom(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String rental_date = request.getParameter("rental_date");
		String rental_time = request.getParameter("rental_time");
		String roomcode = request.getParameter("roomcode");
		int studentcode = Integer.parseInt(request.getParameter("studentcode")); 
		int persons = Integer.parseInt(request.getParameter("persons")); 
		String studentcodes = request.getParameter("studentcodes");
		String reason = request.getParameter("reason");
		
		RentalRoom rentalroom = new RentalRoom(rental_date,rental_time,roomcode,studentcode,
				persons,studentcodes, reason);
		
		/*int result = new RentalRoomBiz().insertRentalRoom(rentalroom);
		if (result > 0) {
			System.out.println("대관완료");
			response.sendRedirect("rentalroom_success.jsp");
		} else {
			System.out.println("대관오류");
		}*/
		
	}

	private void doGoRentalRoomForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RentalRoom rentalroom = new RentalRoom();
	
		String roomcode = request.getParameter("roomcode");
		String rental_date = request.getParameter("date");
		String rental_time = request.getParameter("time");
		rentalroom.setRoomcode(roomcode);
		rentalroom.setRental_date(rental_date);
		rentalroom.setRental_time(rental_time);
		
		session.setAttribute("rentalroom", rentalroom);
		response.sendRedirect("rentalSpaceForm.jsp");
	}

	private void doGetRoomInfo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String location = request.getParameter("location");
		String date = request.getParameter("choice_date");
		
		List<RoomInfo> roominfoList = new RoomInfoBiz().getRoomInfo(location);
		List<RentalRoom> rentalroomList = new RentalRoomBiz().getRentalRoom(date);
		session.setAttribute("roominfoList", roominfoList);
		session.setAttribute("rentalroomList", rentalroomList);
		session.setAttribute("choice_date", date);
		response.sendRedirect("rentalSpace2.jsp");
		
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response)
	{
			
		}
}
