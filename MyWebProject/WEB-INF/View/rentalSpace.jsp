<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  
<title>렌탈</title>
<script type="text/javascript">
$(document).ready(function(){
	$(function() {
		 $( "#datepicker" ).datepicker({ minDate: 0, maxDate: "+3D" ,
		    	showButtonPanel: true});
		  });
	
	$("#submit_menu").click(function(){
		var date = document.getElementById("datepicker").value;
		var location = $("#choice_location > option:selected").val();
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
.content{
	width: 1000px;
	height: auto; /* 500px */
	margin: 10px auto;
	overflow: hidden;
}
.left_content{
	width: 150px;
	height: 300px;
	float: left;
}
.left_content1{
	width: 150px;
	height: 150px;
	background-color: #B70000;
}
.left_content1 h3,.left_content1 h6{
	color: white;
	text-align: center;
}
.left_content1 h3{
	line-height: 50px;
}
.left_content2{
	width: 150px;
	height: 100px;
	margin: 0 auto;
	border: 1px solid #D5D5D5;
}
.left_content2 ul li{
	margin-top: 10px;
}
.main_content{
	width: 750px;
	height: auto; /* 500px */
	margin: 0 20px;
	float: left;
}
.main_content1{
	height: 40px;
	line-height: 40px;
	font-weight: bold;
	border-bottom: 3px solid #6B6B6B;
}
.main_content2{
	height: 40px;
	overflow: hidden;
	margin:10px auto;
	padding-left: 150px;
}
.choice_menu{
	margin: 10px auto;
	float: left;
}

</style>
</head>
<body>
	
	<div class="Header"></div>
	
	<div class="content">
		<div class="left_content">
			<div class="left_content1">
				<h3>대관</h3><h6>Rental-Space</h6>
			</div>
			<div class="left_content2">
				<ul>
				<li><a href="rentalSpace.do">대관</a></li>
				<li><a href="notify.do">공지사항 및<br>주의사항</a></li>
				</ul>
			</div>
		</div>
		
		<div class="main_content">
			<div class="main_content1">대관</div>
			<div class="main_content2">
				<form action="choice.do" method="get">
					<select name="location" class="choice_menu" id="choice_location">
					<option value="M" selected="selected">경상대</option>
					<option value="H">인문대</option>
					<option value="S">과학기술대</option>
					<option value="L">중앙도서실</option>
					</select>&nbsp;&nbsp;
					<p class="choice_menu">Date: <input type="text" name="choice_date" id="datepicker"></p>
					<input type="submit" id="submit_menu" class="choice_menu" value="선 택">
				</form>
			</div>		
		</div>
	</div>
	
	<div class="Footer"></div>
</body>
</html>