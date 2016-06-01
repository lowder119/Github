package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UserInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("���ͼ���!!");
		
		Object confirm = request.getSession().getAttribute("studentinfo");
		
		if(request.getRequestURI().equals("/MyWebProject/mainpage.do")){
			System.out.println("�α������ͼ���");
			return true;
		}
		
		if(confirm == null){
			response.sendRedirect("redirect:/Login.html");
			return false;
		}
		else{
			return true;
		}
	}
	
}
