<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>공지사항/주의사항</title>
<style type="text/css">
@IMPORT url("css/notify.css");
/* width: 600px; */
.goNotify_Attention{
	float: right;
	border-radius: 5px;
}
.NA_menu{
	margin-top: 40px;
	border-top: 3px solid #FFFF48;
	overflow: hidden;
}
.NA_menu_nav:nth-child(1), .NA_menu_nav:nth-child(3), .NA_menu_nav:nth-child(5), .NA_menu_nav:nth-child(7){
	width: 80px;
	float:left;
	background-color: #D5D5D5;
	text-align: center;
}
.NA_menu_nav:nth-child(4), .NA_menu_nav:nth-child(6), .NA_menu_nav:nth-child(8){
	width: 115px;
	float:left;
	text-align: center;
}
.NA_menu_nav:nth-child(2){
	width: 500px;
	float: left;
	text-align: center;
}
.NA_menu_nav:last-child{
	text-align: center;
} 

</style>
</head>
<body>
	<%@ include file="main.jsp" %>
	
	<%Notify_Attention notify_attention = (Notify_Attention)session.getAttribute("notifyAttention"); %>
	
	<%-- <jsp:useBean id="notify" class="com.entity.Notify_Attention" scope="page"/>
	<jsp:setProperty property="*" name="notify"/>  --%>
	
	<div class="content">
		<div class="left_content">
			<div class="left_content1">
				<h3>대관</h3><h6>Rental-Space</h6>
			</div>
			<div class="left_content2">
				<ul>
				<li><a href="rentalSpace.jsp">대관</a></li>
				<li><a href="notify_attention.jsp">공지사항 및<br>주의사항</a></li>
				</ul>
			</div>
		</div>
		
		<div class="main_content">
			<div class="main_content1">공지사항 및 주의사항</div>
			<form action="notify_attention">
			<div><input type="submit" class="goNotify_Attention" value="목록"></div>
			</form>
			<div class="NA_menu">
				<div class="NA_menu_nav">제목</div>
				<div class="NA_menu_nav"><%=notify_attention.getTitle()%></div>
				<div class="NA_menu_nav">작성자</div>
				<div class="NA_menu_nav"><%=notify_attention.getWriter()%></div>
				<div class="NA_menu_nav">작성일</div>
				<div class="NA_menu_nav"><%=notify_attention.getRegDate()%></div>
				<div class="NA_menu_nav">조회수</div>
				<div class="NA_menu_nav">20(수)</div>
				
				<div class="NA_menu_nav"><%=notify_attention.getContent()%></div>
			</div>
		</div>
	</div>
</body>
</html>