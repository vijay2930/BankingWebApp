/**
 * 
 */
 function showPass(id){
	debugger;
	var input;
	console.log(id);
	var icon=document.getElementById(id);
	if(id=="pass1;"){
	     input=document.getElementById("password1");
	}else{
		input=document.getElementById("password2");
	}
	
	if(input.type=="password"){
		input.type="text";
		icon.classList.remove("fa-eye-slash");
		icon.classList.add("fa-eye");
	}else{
		input.type="password";
		icon.classList.remove("fa-eye");
		icon.classList.add("fa-eye-slash");
	}
}
 
function signUp(){
	debugger;
	var fName=document.getElementById("f-name").value;
	var lName=document.getElementById("l-name").value;
	var age=document.getElementById("age").value;
	var dob=document.getElementById("dob").value;
	var address=document.getElementById("address").value;
	var nationality=document.getElementById("nationality").value;
	var adhaarNo=document.getElementById("adhaar-no").value;
	var password=document.getElementById("password1").value;
	var xml=new XMLHttpRequest;
	debugger;
	xml.onreadystatechange=function(){
		if(this.readyState==4 && this.status==200){
			var respon=this.responseText;
			if(respon=="success"){
				console.log("updated");
			}
		}
	}
	xml.open("post","LoginServlet",true);
	xml.setRequestHeader("Content-Type","application/json");
	var action="signUp";
	var data=JSON.stringify({
		"userName": action,
		 "fName": fName,
		 "lName": lName,
		 "age" : age,
		 "dob" : dob,
		 "address": address,
		 "nationality": nationality,
		 "adhaarNo": adhaarNo,
		 "password": password
			});
	xml.send(data);
	}

function checkPass(){
	debugger;
	var pass1=document.getElementById("password1").value;
	var pass2=document.getElementById("password2").value;
	if(pass1!=pass2){
		document.getElementById("checkText").style.display="flex";
		document.getElementsByTagName("form")[0].style.height="1300px";
		document.getElementById("sign-up").disabled=true;
	}else{
		document.getElementById("checkText").style.display="none";
		document.getElementsByTagName("form")[0].style.height="1260px";
		document.getElementById("sign-up").disabled=false;
	}
}