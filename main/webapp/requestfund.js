function checkAccNumber() {
	let content = document.getElementById("account-number").value;
	if (content.length > 0) {
		var xml = new XMLHttpRequest;
		xml.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseText);
				let response = JSON.parse(this.responseText);
				let type = response.accFound;
				document.querySelector(".account-details").style = "display:inline-block";
				document.querySelector(".receiver-account").style = "display:none";
				document.querySelector(".account-input").style = "margin-left:250px";
				document.querySelector(".container").style = "display:flex";
				document.querySelector(".container").style = "align-content=space-evenly";
				if (type == "false") {
					receiverNotFound();
				} else if (type == "ownAccount") {
					ownAccount();
				} else if (type == "true") {
					document.querySelector(".description").style="display:inline-block";
					document.querySelector(".acc-label").style="margin-top:62px";
					document.querySelector(".receiver-account").style = "display:inline-block";
					document.getElementById("acc-holder-name").innerHTML = "Account Holder: " + response.receiverName;
					document.getElementById("acc-no").innerHTML = "Acc-No: " + response.receiverAccNo;
					document.getElementById("age").innerHTML = "Age: " + response.receiverAge;
					document.getElementById("dob").innerHTML = "DOB: " + response.receiverDOB;
				}
			}
		}
		xml.open("post", "TransferFundServlet", true);
		xml.setRequestHeader("content-type", "application/x-www-form-urlencoded", true)
		xml.send("action=findacc&transferAccNo=" + content);
	}
}
function isValid() {
	console.log("hi");
	let amountBox = document.getElementById("amount");
	let amount = amountBox.value;
	let button = document.getElementById("submitButton");
	if (amount < 1 || amount > 100000) {
		button.disabled = true;
		document.getElementById("inputMsg").innerHTML = "Enter the valid amount(₹ 1 to 100000)";
	} else {
		document.getElementById("inputMsg").innerHTML = "Enter the amount you want to transfer";
		button.disabled = false;
	}
}
var sendAmount;

function transfer() {
	debugger;
	let receiver = document.getElementById("account-number").value;
	let amount = document.getElementById("amount").value;
	let description=document.getElementById("des-send").value;
	var xml = new XMLHttpRequest;
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			var result = this.responseText;
			if (result == "sent") {
				sendAmount=amount;
				sentSuccessfully();
			}
		}
	}
	xml.open("post", "UserServlet", true);
	xml.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xml.send("action=requestfund&amount=" + amount + "&receiver=" + receiver+"&description="+description);
}

function sentSuccessfully(){
	document.getElementById("imager").src="request.png";
	document.getElementById("message").innerHTML="Request of ₹ "+sendAmount+" sent successfully";
	document.querySelector(".receiver-account").style="display:none";
	document.querySelector(".msg").style="display:block";
	document.querySelector(".account-input").style="display:none";
	document.querySelector("#detail-tag").style.marginLeft = "39em";
}


function receiverNotFound() {
	document.getElementById("imager").src = "not-found.png";
	document.getElementById("message").innerHTML = "No account present for the give account number";
	document.querySelector(".receiver-account").style = "display:none";
	document.querySelector(".msg").style = "display:block";
}

function ownAccount() {
	document.getElementById("message").innerHTML = "You cannot request money from your own account";
	document.querySelector(".receiver-account").style = "display:none";
	document.querySelector(".msg").style = "display:block";
}