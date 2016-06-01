<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.entity.*,java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
@IMPORT url("css/notify.css");
</style>
</head>
<body>
	<%@ include file="main.jsp" %>
	<% List<Notify_Attention> notifyAttention_list = (List<Notify_Attention>)session.getAttribute("notifyAttention_list");%>
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
		
		<form action="notify_attention_content.jsp" method="post">
		<div class="main_content">
			<div class="main_content1">공지사항 및 주의사항</div>
			<div class="main_content2">
				<div class="main_no">번호</div>
				<div class="main_title">제목</div>
				<div class="main_rigDate">등록일자</div>
				<div class="main_writer">작성자</div>
				<div class="main_count">조회수</div>
			</div>
			<%for(Notify_Attention list : notifyAttention_list){ %>
			<div class="main_content2">
				<div class="main_no"><%=list.getwriting_Number()%></div>
				<div class="main_title"><a href="notify_attention_content?writing_number=<%=list.getwriting_Number()%>"><%=list.getTitle()%></a></div>
				<div class="main_rigDate"><%=list.getRegDate() %></div>
				<div class="main_writer"><%=list.getWriter() %></div>
				<div class="main_count">??</div>
			</div>
			<%} %>
		</div>
		</form>
	</div>
</body>
</html>