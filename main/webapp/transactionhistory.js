/**
 * 
 */
function showTransactionHistory() {
	var xml = new XMLHttpRequest;
	xml.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			var trans = JSON.parse(this.responseText);
			modifyDataInTable(trans);
		}
	}
	xml.open("post", "UserServlet", true);
	xml.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xml.send("action=showTransactionHistory");
}
function modifyDataInTable(trans) {
	debugger;
	for (let i = trans.length-1; i >-1; i--) {
		var type = trans[i].type;
		var amount = trans[i].amount;
		var date = trans[i].date;
		var time = trans[i].time;
		var balance = trans[i].balance;
		var tbody = document.createElement("tbody");
		var tdate = document.createElement("td");
		var ttime = document.createElement("td");
		var tamount = document.createElement("td");
		var tdescription = document.createElement("td");
		var tbalance = document.createElement("td");
		tdate.innerHTML=date;
		ttime.innerHTML=time;
		tamount.innerHTML=amount;
		tdescription.innerHTML=type;
		tbalance.innerHTML=balance;
		tdate.classList.add("t-date");
		ttime.classList.add("t-time");
		tamount.classList.add("t-amount");
		tdescription.classList.add("t-des");
		tbalance.classList.add("t-balance");
		tdate.classList.add("inside-table");
		ttime.classList.add("inside-table");
		tamount.classList.add("inside-table");
		tdescription.classList.add("inside-table");
		tbalance.classList.add("inside-table");
		tbody.appendChild(tdate);
		tbody.appendChild(ttime);
		tbody.appendChild(tamount);
		tbody.appendChild(tdescription);
		tbody.appendChild(tbalance);
		document.getElementById("tabs").appendChild(tbody);
	}
	


}