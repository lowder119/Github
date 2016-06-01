function check_Login(){
	if(document.getElementById("studentCode").value==""){
		alert("Input your ID");
		document.getElementById("studentCode").focus();
		return;
	}else{
		if(validateID()!=false){
			if(document.getElementById("pw").value==""){
				alert("Input your PassWord");
				document.getElementById("pw").focus();
				return;
			}/*else if(validatePW()!= false){
				alert("Input your Password through RegExp");
				alert("Input your pw [a-zA-Z0-9!@#$%]{1,}");
				document.getElementById("pw").focus();
				return;
			}*/
		}else{
			return;
		}
	}
	login_form.submit();//frm은 from의 이름
}

//유효성검사(정규표현식)--------------------------------------------------------
function validateID(){
	var objID = document.getElementById("studentCode");
	var objRegExp = new RegExp(/^[0-9]{10}/);
		//정규표현식 ^ : 시작
		//       $ : 끝
	
	var bResult = objRegExp.test(objID.value);
	
	if(!bResult){
		alert("Input your StudentCode 10");
		objID.value="";
		objID.focus();
	}
	return bResult;
}

/*function validatePW(){
	var objPW = document.getElementById("pw");
	var objRegExp = new RegExp(/^[a-zA-Z0-9!@#$%]{1,}$/);
	//[] 를 라인단위로 받는다. 단 {} 표시로 숫자 범위를 표시해줘야 함
	var bResult = objRegExp.test(objPW.value);
	
	if(!bResult){
		alert("Input your pw [a-zA-Z0-9!@#$%]{1,}");
		objPW.value="";
		objPW.focus();
	}
	return bResult;
}*/