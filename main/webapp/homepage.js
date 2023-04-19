/**
 * 
 */
//checkBalance(){
//	 xml.open("post","")
// }
let res;
function load() {
	var xml = new XMLHttpRequest;
	xml.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			console.log(this.response);
			res = JSON.parse(this.responseText);
			console.log(res);
			dataCall();
		}
	}

	xml.open("post", "http://localhost:8080/bank-project/UserDetailServlet", true);
	xml.send();
}

function requestMoney() {
	window.location.href = "http://localhost:8080/bank-project/requestfund.html";
}
function transferMoney() {
	debugger;
	window.location.href = "http://localhost:8080/bank-project/transferfund.html";
}


function balanceCheck() {
	debugger;
	debugger;
	var xml = new XMLHttpRequest;
	xml.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var balance = this.responseText;
			document.querySelector('.for-balance').style = "display:none";
			document.querySelector(".balance-dp").style = "display:block";
			document.querySelector(".balance").style = "display:block";
			document.querySelector(".balance").innerHTML = "Balance <br> &#x20B9; " + balance + " /-";
			progressBarLoad();
		}
	}
	xml.open("post", "UserServlet", true);
	xml.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xml.send("action=checkBalance");
}
function progressBarLoad() {
	debugger;
	var progressBar = document.getElementById("p-bar");
	progressBar.value = 0;
	console.log(progressBar.style.display);
	if (progressBar.style.display != "block") {
		progressBar.style.display = "block";
		var count = 0;
		var interval = setInterval(function() {
			count = count + 0.3;
			progressBar.value = count;
			if (count >= 100) {
				progressBar.style.display = "none";
				document.querySelector('.for-balance').style = "display:block";
				document.querySelector(".balance-dp").style = "display:none";
				document.querySelector(".balance").style = "display:none";
				progressBar.style.display = "none";
				clearInterval(interval);

			}
		}, 15);
	}

}



function viewMoneyRequest() {
	window.location.href = "http://localhost:8080/bank-project/moneyrequest.html";
}

function showTransactionHistory() {
	window.location.href = "http://localhost:8080/bank-project/transactionhistory.html";
}
function dataCall() {
	var details = res.accDetails;
	console.log(details);
	var name = details.name;
	var DOB = details.DOB;
	var accNo = details.accNo;
	var age = details.age;
	document.getElementById("userName").innerHTML = "Account holder: " + name;
	document.getElementById("userAccountNumber").innerHTML = "Account Number: " + accNo;
	document.getElementById("userAge").innerHTML = "Age: " + age;
	document.getElementById("userDOB").innerHTML = "Date of Birth: " + DOB;
}