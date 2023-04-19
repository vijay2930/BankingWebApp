/**
 * 
 */

 function login(){
	 let userName=document.getElementById("accno").value;
	 let password=document.getElementById("password").value;
	 let xml=new XMLHttpRequest();
	 xml.onreadystatechange=function(){
		 if(this.readyState==4 && this.status==200){
			 console.log("userValidationsSuccess");
			 let res=this.responseText;
			 if(res!="not found"){
			 window.location.href="http://localhost:8080/bank-project/homepage.jsp";
			 }else{
				alert("user and password mismatch"); 
			 }
			 console.log(this.responseText);
		 }
	 }
	 debugger;
	 console.log(userName);
	 console.log(password);
	 xml.open("post","http://localhost:8080/bank-project/LoginServlet",true);
	 xml.setRequestHeader("content-type","application/x-www-form-urlencoded");
	 xml.send("userName="+userName+"&password="+password);
 }
 
 function showPass(){
	debugger;
	var pass=document.getElementById("password");
	var eye=document.querySelector(".fa");
	if(pass.type=="password"){
		pass.type="text";
		eye.classList.remove("fa-eye");
		eye.classList.add("fa-eye-slash");
	}else{
		pass.type="password";
		eye.classList.add("fa-eye");
		eye.classList.remove("fa-eye-slash");
	}
}
 