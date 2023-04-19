package com.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.dto.Account;
import com.dto.MoneyRequest;
import com.dto.TransactionHistory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.login.LoginController;

/**
 * Servlet implementation class BalanceServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private HttpServletRequest req;
	private HttpServletResponse res;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
    
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 HttpSession session=request.getSession();
		 Account acc=(Account) session.getAttribute("acc");
		 UserController user=new  UserController(this);
	     this.req=request;
		 this.res=response;
		// TODO Auto-generated method stub
		String type=request.getParameter("action");
		switch(type) {
		case "checkBalance":{
			user.getBalance(acc,request,response);
			break;
		}
		case "showTransactionHistory":{ 
			user.showTransactions(acc);
			break;
		}
		case "viewMoneyRequest":{
			user.findRequest(acc);
			break;
		}
		case "requestfund":{
		   requestMoney();
		   break;
		}
		
		default:{
			System.out.println("error in request");
		}
		}
	}

	private void requestMoney() {
		HttpSession session=req.getSession();
	   int currAcc=((Account)session.getAttribute("acc")).getAccountNumber();
	  // xml.send("action=requestfund&amount=" + amount + "&receiver=" + receiver);
	   int acc=Integer.parseInt(req.getParameter("receiver"));
	   int amount=Integer.parseInt(req.getParameter("amount"));
	   String description=req.getParameter("description");
	   UserController uc=new UserController(this);
	   uc.requestMoney(amount, acc, description, currAcc);
	}

	public void showBalance(long balance,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("im in show balance");
		try {
		response.setContentType("text/plain");
		PrintWriter out=response.getWriter();
		out.print(balance);
		}catch (Exception e) {
			System.out.println("error in showbalance call");
			e.printStackTrace();
		}
	}

	public void showTransactions(List<TransactionHistory> trans) {
		System.out.println("im in show transactionhistory");
		res.setContentType("application/json");
		JsonArray js=new JsonArray();
		ObjectMapper obj=new ObjectMapper();
		try {
			String ja=obj.writeValueAsString(trans);
			PrintWriter out=res.getWriter();
			out.print(ja);
			}catch (Exception e) {
				System.out.println("error in showTrans call");
				e.printStackTrace();
			}
	}

	public void showRequest(List<MoneyRequest> requests) {
		res.setContentType("application/json");
		JsonArray js=new JsonArray();
		ObjectMapper obj=new ObjectMapper();
		try {
			String sender=obj.writeValueAsString(requests);
			System.out.println(sender.toString());
			PrintWriter out=res.getWriter();
			out.print(sender);
		}catch(Exception e) {
			System.out.println("error in showRequest");
			e.printStackTrace();
		}
	}

	public void requestSucess(long amount, int accNo) {
		res.setContentType("text/plain");
		try {
		PrintWriter out=res.getWriter();
		out.print("sent");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
