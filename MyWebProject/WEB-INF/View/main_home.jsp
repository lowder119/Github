<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.List,com.entity.NotifyInfo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>HTML5</title>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="jquery.bxslider/jquery.bxslider.min.js"></script>
<link rel="stylesheet"
	href="jquery.bxslider/jquery.bxslider.css" type="text/css" />
<script>
	$(document).ready(function() {
		$('.bxslider').bxSlider({
			auto: true,
			autoControls: true
		});
		var headerUrl = "main.do";
		$.get(headerUrl,function(data){
			$(".Header").html(data);
		});
		var footerUrl = "footer.do";
		$.get(footerUrl,function(data){
			$(".Footer").html(data);
		});
		return false;
	});
</script>
<style type="text/css">
.img_slide{
	width: 800px;
	margin: 0 auto;
}
.img_slide li img{
	width: 800px;
	height: 200px;
}
.notify{
	width: 800px;
	height: 200px;
	margin: 0 auto;
}
.notify_top{
	width: 800px;
	height: 50px;
	overflow: hidden;
}
.notify_top span{
	float: left;
	line-height: 50px;
}
.notify_top img{
	width: 40px;
	height: 40px;
	float: right;
}
.notify_title{
	width: 800px;
	height: 20px;
}

.main_content{
	margin: 0 auto;
}
.main_content1{
	height: 40px;
	line-height: 40px;
	font-weight: bold;
}
.main_content2{
	height: auto;
	overflow: hidden;
	margin-top: 5px;
}
.main_content2 div{
	border: 1px solid #D5D5D5;
}
.main_no{
	width: 70px;
	float: left;
	text-align: center;
}
.main_title{
	width: 430px;
	float: left;
	text-align: center;
}
.main_rigDate{
	width: 130px;
	float: left;
	text-align: center;
}
.main_writer{
	width: 80px;
	float: left;
	text-align: center;
}
.main_count{
	width: 80px;
	float: left;
	text-align: center;
}

</style>
</head>


<body>
	
	<div class="Header"></div>
	<div class="img_slide">
	<ul class="bxslider">
		<li><img src="img/room/L/L103.PNG" /></li>
		<li><img src="img/room/L/L104.PNG" /></li>
		<li><img src="img/room/M/M103.PNG" /></li>
		<li><img src="img/room/M/M303.PNG" /></li>
		<li><img src="img/room/L/L304.PNG" /></li>
		<li><img src="img/room/M/M403.PNG" /></li>
	</ul>
	
		<div class="main_content">
			<div class="main_content1">공지사항 및 주의사항</div>
			<div class="main_content2">
				<div class="main_no">번호</div>
				<div class="main_title">제목</div>
				<div class="main_rigDate">등록일자</div>
				<div class="main_writer">작성자</div>
				<div class="main_count">조회수</div>
			<%-- <%for(Notify_Attention list : notifyAttention_list){ %> --%>
			<c:forEach items="${NotifyInfoList}" var="list">
				<div class="main_no">${list.getwriting_Number()}</div>
				<div class="main_title"><a href="notify_attention_content?writing_number=${list.getwriting_Number()}">${list.getTitle()}</a></div>
				<div class="main_rigDate">${list.getRegDate()}</div>
				<div class="main_writer">${list.getWriter()}</div>
				<div class="main_count">??</div>
			</c:forEach>	
			</div>
	
			<%-- <%} %>  --%>
		</div>
	</div>
	<div class="Footer"></div>
	
</body>
</html>