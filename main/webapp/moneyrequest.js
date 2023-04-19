/**
 * 
 */
 
 
 
 function transfer(i){
	debugger;
	let receiver = req[i].fromAccNo;
	let amount = req[i].amount;
	var xml = new XMLHttpRequest;
	xml.onreadystatechange = function() {
		if (xml.readyState == 4 && xml.status == 200) {
			var result = this.responseText;
			if (result == "sent") {
				sendAmount=amount;
				sentSuccessfully();
			} else if (result == "inSufficientFunds") {
				insufficientFunds();
			}
		}
	}
	xml.open("post", "TransferFundServlet", true);
	xml.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xml.send("action=transferfund&amount=" + amount + "&receiver=" + receiver);
}
function sentSuccessfully(){
	document.querySelector(".tabular").style="display:none";
	document.querySelector(".result-box").style="display:block";
	document.querySelector(".result-text").innerHTML="Money sent successfully";
}
function insufficientFunds(){
	document.querySelector(".tabular").style="display:none";
	document.querySelector(".result-box").style="display:block";
	document.querySelector(".result-text").innerHTML="Money sent successfully";
}

var req;
function showMoneyRequest() {
	var xml = new XMLHttpRequest;
	xml.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			req = JSON.parse(this.responseText);
			modifyDataInTable(req);
		}
	}
	xml.open("post", "UserServlet", true);
	xml.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xml.send("action=viewMoneyRequest")
}

function modifyDataInTable(req) {
	debugger;
	console.log(req)
	for (let i = req.length-1; i >=0; i--) {
		var reqs=[];
		reqs.push(req[i].requestId);
		reqs.push(req[i].fromAccNo);
		reqs.push(req[i].date);
		reqs.push(req[i].time);
		reqs.push(req[i].amount);
		reqs.push(req[i].description);
		reqs.push(req[i].paid);
		var tbody = document.createElement("tbody");
		for (let j = 0; j < reqs.length; j++) {
			var tdata = document.createElement("td");
			var data = reqs[j];
			if (j == reqs.length - 1) {
				tdata.classList.add("ted");
				tdata.classList.add("inside-table");
				var button=document.createElement("button");
				if(data==true){
					button.innerHTML="Paid";
					button.classList.add("paid-button");
				}else{
					button.innerHTML="Pay";
					button.classList.add("pay-button");
					button.addEventListener("click",function() { transfer(i); });
				}
				tdata.appendChild(button);
			} else {
				tdata.innerHTML = data;
				if (j == reqs.length - 2) {
					tdata.classList.add("t-des");
				} else {
					tdata.classList.add("ted");
				}
				tdata.classList.add("inside-table");
			}
			tbody.appendChild(tdata);
		}
		document.getElementById("tabs").appendChild(tbody);
	}
}