<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "com.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script><!-- //라이브러리를 가져온다 -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">

<title>Insert title here</title>

<script type="text/javascript" src="js/rentaljs.js">

	$(document).ready(function(){
/* 		$("#persons_input").focus(function(){
			 $(this).css("background-color", "#cccccc");
			 $("#persons_ex").attr("display","inline"); 
			 
		});
		$("#persons_input").blur(function(){
			 $(this).css("background-color", "#ffffff");
			 $("#persons_ex").attr("display","none");
		});
		$("#studentcodes_input").focus(function(){
			 $(this).css("background-color", "#cccccc");
			 $("#studentcodes_ex").attr("display","inline");
		});
		$("#studentcodes_input").blur(function(){
			 $(this).css("background-color", "#ffffff");
			 $("studentcodes_ex").attr("display","none");
		});
		$("#reason_input").focus(function(){
			 $(this).css("background-color", "#cccccc");
			 $("#reason_ex").attr("display","inline");
		});
		$("#reason_input").blur(function(){
			 $(this).css("background-color", "#ffffff");
			 $("#reason_ex").attr("display","none");
		}); */
		
	    var tooltips = $( "[title]" ).tooltip({
	        position: {
	          my: "left top",
	          at: "right+5 top-5"
	        }
	      });
		
	});
</script>

<style type="text/css">
body {
   font-family:'Times New Roman', Times, serif;
   margin:0;
}
legend{
	border-color: #ED0000;	
}
.top_rentalform{
	width: 100%;
	background-color: #B70000;
}
fieldset{
	border-color: #FFA2A2;
	margin-top: 10px;
}
.rental_form{
	margin-top: 10px;
}
.rental_form td, input, textarea{
	text-align: center;
	border-radius: 5px;
}
.ui-tooltip {
    width: 250px;
    font-size: 0.8em;
}
.check_wrap{
	width: 300px;
	height: 60px;
	margin-top:10px;
	overflow: hidden;
}
.checkbox{
	width:50px;
	float: left;
	line-height: 100px;
}
.check_ex{
	width:230px;
	float: left;
	font-size: 0.8em;
}
.bottom_ex{
	margin: 20px auto;
	width: 100%;
	height: 50px;
	position:absolute;
	background-color: #8C8C8C;
}

</style>
</head>
<body>

	<%StudentInfo studentinfo = (StudentInfo)session.getAttribute("studentinfo"); %>
	<%RentalRoom rentalroom = (RentalRoom)session.getAttribute("rentalroom"); %>
	 
	<div class="top_rentalform">
		<img  src="img/mark/KoreaMark.png">
	</div>
	 
		<fieldset class="log_border">
		<legend>대관진행</legend>
		<form name="rental_frm" action="rentalroom">
		<table class="rental_form">
			<tr>
			<td>대관날짜</td>
			<td><input type="text" name ="rental_date" value="<%=rentalroom.getRental_date()%>" readonly="readonly"></td>
			<td></td>
			</tr>
			<tr>
			<td>대관시간</td>
			<td><input type="text" name ="rental_time" value="<%=rentalroom.getRental_time()%>" readonly="readonly"></td>
			<td></td>
			</tr>
			<tr>
			<td>대관장소</td>
			<td><input type="text" name="roomcode" value="<%=rentalroom.getRoomcode()%>" readonly="readonly"></td>
			<td></td>
			</tr>
			<tr>
			<td>대관자 학번</td>
			<td><input type="text" name="studentcode" value="<%=studentinfo.getStudentCode()%>" readonly="readonly"></td>
			<td></td>
			</tr>
			<tr>
			<td>참여인원</td>
			<td><input type="text" name ="persons" title="정확한 인원수를 입력하세요" id="persons_input"></td>
			</tr>
			<tr>
			<td>참여인원 학번</td>
			<td><input type="text" name="studentcodes" title="참여인원 모두의 학번을 입력하세요" id="studentcodes_input"></td>
			</tr>
			<tr>
			<td>대관이유</td>
			<td><textarea rows="3" name="reason" cols="20" id="reason_input" title="대관목적을 상세히 입력하세요"></textarea></td>
			</tr>
		</table>
		</form>
			<div class="check_wrap">
			<div class="checkbox"><input type="checkbox" id="rental_check"></div>
			<div class="check_ex"><span>"대관자"는 대관주의사항을 확인했으며 학교 시설물을 내것처럼 사용할 것을 약속합니다.</span></div>	
			</div>
			
			<input type="button" value="예약하기" onclick="rentalForm_Check()">
		</fieldset>		
		
		<div class="bottom_ex">
		
		</div>
		
	
	
</body>
</html>