function rentalForm_Check(){
	if(document.getElementById("persons_input").value==""){
		alert("1");
		document.getElementById("persons_input").focus();
		return;
	}
	if(document.getElementById("studentcodes_input").value==""){
		alert("2");
		document.getElementById("studentcodes_input").focus();
		return;
	}
	if(document.getElementById("reason_input").value==""){
		alert("3");
		document.getElementById("reason_input").focus();
		return;
	}
	if(!document.getElementById("rental_check").checked){
		alert("4");
		document.getElementById("rental_check").focus();
		return
	}
	rental_frm.submit();//rental_frm은 from의 이름
}