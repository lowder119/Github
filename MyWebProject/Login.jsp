<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script><!-- //라이브러리를 가져온다 -->
<style type="text/css">
@IMPORT url("css/myCss.css");
</style>

<c:if test="${errCode==null}">
	<c:set var="errCode" value="\"\""></c:set>
</c:if> 

<script type="text/javascript">
	function checkErrCode(){
		var errCode = <%=request.getParameter("errCode")%>
		if(errCode != null || errCode != ""){
			switch(errCode){
			case 1:
				alert("학번을 다시 확인하세요");
				break;
			case 2:
				alert("비밀번호가 일치하지 않습니다.!");
				break;
			case 3:
				alert("고려대DB에 없는 학번");
				break;
			}
		}
	}
</script>

<script type="text/javascript">
	$(document).ready(function changeimg(){
		$(".main_left").fadeTo(3000,0,function(){
			$(".main_left").attr("background-image","img/log(2).PNG").fadeTo(3000,1).fadeTo(3000,0,function(){
				$(".main_left").attr("background-image","img/log(1).PNG").fadeTo(3000,1,changeimg);
			});
		});
	});
</script>
</head>

<body onload="checkErrCode()">
	<div class="header">
	<p><img alt="KUPID" src="img/Koreamain.png"></p>
	<h3>Korea University Rental System</h3>
	</div>
	<hr/>
	
	<br/>
	
<div class="wrap">
	
	<div class="main_left">
	</div>
	
	<div class="main_right">
	<form action="mainpage.do" method = "post" name = "login_form">
		<fieldset class="log_border">
		<legend>Login</legend>
		<table>
			<tr>
				<td><input type = "text" name = "studentCode" id="studentCode" size= "10" maxlength="10" /></td>
				<td><input type = "password" name = "pw"  id="pw" size="10"/></td>	
			</tr>
			<tr>
				<td><input type="radio" name ="Confirm_Who" value = "Student" checked ="checked">학생
					<input type="radio" name ="Confirm_Who" value = "Employee">직원</td>
			</tr>
			<tr>
				<td colspan ="2" align="right"><input type="submit" value="로그인"></td>
			</tr>
		</table>
		</fieldset>	
	</form>
	</div>
</div>

	
</body>
</html>