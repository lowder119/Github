<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.entity.*,java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  
<title>��Ż2</title>
<script type="text/javascript">

function showPopup(roomcode,time){
	var date = document.getElementById("datepicker").value; //valueof() -> HTML�Լ� , innertext -> �±׾� ���� , value ->�±��� value��
	window.open("doGoRentalRoomForm?roomcode="+roomcode+"&date="+date+"&time="+time, 
			"Form", "width=350, height=500, left=100, top=50");
	
	}  

$(document).ready(function(){
	  $(function() {
		    $( "#datepicker" ).datepicker({ minDate: 0, maxDate: "+3D" ,
		    	showButtonPanel: true});
		  });
	
	$("#submit_menu").click(function(){
		var date = document.getElementById("datepicker").value;
		var location = $("#choice_location > option:selected").val();
	});
	
});
</script>
<style type="text/css">
.content{
	width: 800px;
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
	margin: 10px auto;
}
.left_content2 ul li{
	margin-top: 10px;
}
.main_content{
	width: 600px;
	height: auto; /* 500px */
	margin: 0 20px;
	float: left;
}
.main_content1{
	height: 40px;
	line-height: 40px;
	font-weight: bold;
}
.main_content2{
	height: 40px;
	overflow: hidden;
	padding-left: 150px;
}
.choice_menu{
	margin: 10px auto;
	float: left;
}
/* �̹��� �� ���� */
.main_content3{
	width: 630px;
	height: auto;
	margin: 0 auto;
	overflow: hidden;
}
.space_img{
	width: 300px;
	height: 150px;
	margin:10px auto;
	float: left;
	/* background-image: url("img/room1.jpg"); */
	background-size: 100% 100%;
}
.space_explain{
	width: 300px;
	height: 150px;
	margin: 10px auto;
	padding-left:10px;
	float: left;
	font-size: 0.9em;
	color: #353535;
}
.space_explain div{
	width: 200px;
	height: 50px;
	margin-top: 30px
	float: left;
}
</style>
</head>
<body>
	<%@include file ="main.jsp" %>
	<%String[] image_name  = {"M103.PNG", "M203.PNG", "M204.PNG","M303.PNG","M403.PNG","M404.PNG"}; %>
	<%List<RoomInfo> roominfoList = (List<RoomInfo>)session.getAttribute("roominfoList");%>
	<%List<RentalRoom> rentalroomList = (List<RentalRoom>)session.getAttribute("rentalroomList");%>
	<%String choice_date = (String)session.getAttribute("choice_date");%>
	
	<div class="content">
		<div class="left_content">
			<div class="left_content1">
				<h3>���</h3><h6>Rental-Space</h6>
			</div>
			<div class="left_content2">
				<ul>
				<li><a href="rentalSpace.jsp">���</a></li>
				<li><a href="notify_attention">�������� ��<br>���ǻ���</a></li>
				</ul>
			</div>
		</div>
		
		<div class="main_content">
			<div class="main_content1">���</div>
			<div class="main_content2">
				<form action="choice" method="get">
					<select name="location" class="choice_menu" id="choice_location">
					<option value="M">����</option>
					<option value="H">�ι���</option>
					<option value="S">���б����</option>
					<option value="L">�߾ӵ�����</option>
					</select>&nbsp;&nbsp;
					<p class="choice_menu">Date: <input type="text" name="choice_date" id="datepicker" value="<%=choice_date%>"></p>
					<input type="button" id="submit_menu" class="choice_menu" value="�� ��">
				</form>
			</div>
			
			
			<div class="main_content3">
			<%int img_index=0; %>
			<%for(RoomInfo r : roominfoList) {%>
				<div class="space_img" style="background-image: url('img/room/M/<%=image_name[img_index++]%>');">
				</div>
				<div class="space_explain">
				��� :<%=r.getRoomcode() %><br>
				���� :<%=r.getArea() %>(m)<br>
				�����ο� : <%=r.getAccommodate_person() %>(��)<br>
				<%=r.getExplaination() %><br><br>
				   <div>
						<span>18~20</span>�� <%int i=0; for(RentalRoom rr : rentalroomList)
						{if(rr.getRental_time().equals("18~20") && rr.getRoomcode().equals(r.getRoomcode()))
						{%>������<% i++; break;}} if(i==0)
						{%><a href="#none" onclick="showPopup('<%=r.getRoomcode()%>','18~20')">�����ϱ�</a><%}%> 
						<br>
						<span>20~22</span>�� <%i=0; for(RentalRoom rr : rentalroomList)
						{if(rr.getRental_time().equals("20~22") && rr.getRoomcode().equals(r.getRoomcode()))
						{%>������<% i++; break;}} if(i==0)
						{%><a href="#none" onclick="showPopup('<%=r.getRoomcode()%>','20~22')">�����ϱ�</a><%}%>
						<br>
						<span>22~24</span>�� <%i=0; for(RentalRoom rr : rentalroomList)
						{if(rr.getRental_time().equals("22~24") && rr.getRoomcode().equals(r.getRoomcode()))
						{%>������<% i++; break;}} if(i==0)
						{%><a href="#none" onclick="showPopup('<%=r.getRoomcode()%>','22~24')">�����ϱ�</a><%}%>
					</div>
				</div>
				
				<%} %>
			</div>
			
		</div>
	</div>
</body>
</html>