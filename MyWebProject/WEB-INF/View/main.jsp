<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import = "com.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style type="text/css">
body {
   font-family:'Times New Roman', Times, serif;
   margin:0;
}
/* div{
	border: 1px solid #D5D5D5;
}   */
a{
	text-decoration: none;
}
.header{
	width: 100%;
	height: 25px;
	margin: 0 auto;
	background-color: #B70000;
	overflow: hidden;
}
.header font{
	float: right;
	margin-right: 40px;
	color: white;
	font-size: 0.8em;
	line-height: 25px;
}
.header2{
	width: 1000px;
	height: 70px;
	margin: 10px auto 0 auto;
	overflow: hidden;
}
.header2_E:nth-child(1){
	float: left;
}
.header2_E:nth-child(1) a{
	text-decoration: none;
}
.header2_E:nth-child(2){
	float: right;
	overflow: hidden;
}
.header2_E:nth-child(2) li{
	float: right;
	font-size: 0.9em;
	margin-left: 30px;
	list-style: none;
	line-height: 40px;
}
.nav{
	width: 1000px;
	height: 50px;
	margin: 0 auto;
	overflow: hidden;
	padding-left: 180px;
}
.nav ul li{
	float: left;
	list-style: none;
	padding-left: 100px;
	font-size: 1.3em;
	font-weight: bolder;
}
.nav ul li a{
	color: #353535;
}
hr{
	margin : 10px auto;
	color: gray;
}
</style>

</head>
<body>
	
 	<div class="header">
 		<font>${studentinfo.getName()}(${studentinfo.getMajor()})님 ON</font>
 	</div>
 	
 	<div class="header2">
 		<div class="header2_E">
 			<a href="home.do"><img src="img/ku-the-future-110-years-and-beyond.png"></a>
 		</div>
 		<div class="header2_E">
 			<ul>
 			  <li><a href="http://www.korea.ac.kr">고려대학교(안암)</a></li>
 			  <li><a href="http://sejong.korea.ac.kr">고려대학교(세종)</a></li>
 			  <li><a href="http://portal.korea.ac.kr">고려대학교(포털)</a></li>
 			</ul>
 		</div>
 	</div>
 	
 	<hr>
 	
 	<div class="nav">
 		<ul>
 		  <li><a href="home.do">메인</a></li>
 		  <li><a href="rentalSpace.do">대관</a></li>
 		  <li><a href="#">대여</a></li>
 		  <li><a href="#">나의대여목록</a></li>
 		</ul>
 	</div>
 	
 	<hr>
	
</body>
</html>