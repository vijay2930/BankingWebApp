<!DOCTYPE html>
<html>
<head>
	<title>Banking Application</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="homepage.css">
</head>
<body onload="load()">
	<header>
		<div class="container" id="top">
			<div class="logo">
				<img class="log" src="logo.png" alt="Banking Application Logo">
			</div>
			<div class="naviContainer">
				<ul>
					<a class="navi" href="#">About us</a>
					<a class="navi" href="#">Contact us</a>
					<a class="navi" href="#">Logout</a>
				</ul>
			</div>
		</div>
	</header>
	<div class="account-details">
		<img class ="balance-dp" src="money-bag.png" alt="User Photo">
		 <h2 class="name balance" >Balance:</h2>
		 <div class="for-balance">
		 <img class ="user-dp" src="dpbank.png" alt="User Photo">
		<h2 class="name2" class="name" id="userName" >Account Holder: Siva</h2>
		<p  id="userAccountNumber">Account Number: 8825846797</p>
		<p  id="userAge">Age: 23</p>
		<p  id="userDOB">Date of Birth: 29/08/2000</p>
		</div>
	  </div>
	  <div class="progress"><progress id="p-bar" value=100 max=100></progress></div>
	<main>
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<div class="card">
						<div class="card-body">
							<h2>Check Balance</h2>
							<p>View your account balance and recent transactions.</p>
							<a href="#top" onclick="balanceCheck()" class="btn btn-primary" id="balanceButton">Check Balance</a>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<div class="card-body">
							<h2>Transfer Money</h2>
							<p>Send money to another account within the same bank or to a different bank.</p>
							<button href="#" onclick="transferMoney()" class="btn btn-primary">Transfer Money</button>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card">
						<div class="card-body">
							<h2>Request Money</h2>
							<p>Request money from another account within the same bank or from a different bank.</p>
							<button href="#" onclick="requestMoney()" class="btn btn-primary">Request Money</button>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="card">
						<div class="card-body">
							<h2>View Money Requests</h2>
							<p>View requests for money that have been sent to you.</p>
							<button href="#" onclick="viewMoneyRequest()" class="btn btn-primary">View Money Requests</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card">
					<div class="card-body">
						<h2>View Transaction History</h2>
						<p>See all your transactions and money requests which are paid and borrowed.</p>
						<button href="#" onclick="showTransactionHistory()" class="btn btn-primary">View Transaction History</button>
					</div>
				</div>
			</div>
		</div>
	</main>
	<footer>
		<div class="container">
			<p>&copy; 2023 Banking Application</p>
		</div>
	</footer>
	<script type="text/javascript" src="homepage.js"></script>
</body>
</html>

</html>