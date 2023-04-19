package com.transferfund;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JsonObject;

import com.dto.Account;

/**
 * Servlet implementation class TransferFundServlet
 */
@WebServlet("/TransferFundServlet")
public class TransferFundServlet extends HttpServlet {
	private HttpServletRequest req;
	private HttpServletResponse res;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TransferFundServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		System.out.println((Account) session.getAttribute("acc"));
		int currAcc = ((Account) session.getAttribute("acc")).getAccountNumber();
		req = request;
		res = response;
		String type=request.getParameter("action");
		switch (type) {
		case "findacc":{
			int accNo = Integer.parseInt(request.getParameter("transferAccNo"));
			if (accNo == currAcc) {
				res.setContentType("application/json");
				JsonObject obj=new JsonObject();
				obj.put("accFound", "ownAccount");
				PrintWriter out=res.getWriter();
				out.print(obj.toJson());
			} else {
				TransferController tc = new TransferController(this);
				tc.checkTransferAccount(accNo);
			}
			break;
		}
		case "transferfund":{
			int receiver=Integer.parseInt(request.getParameter("receiver"));
			int amount=Integer.parseInt(request.getParameter("amount"));
			System.out.println(receiver);
			System.out.println(amount);
			System.out.println(currAcc);
			TransferController tc=new TransferController(this);
			tc.checkAmount(amount,receiver,currAcc);
			break;
		  }
		}
	}

	protected void transferAccFound(Account acc) {
		res.setContentType("application/json");
		JsonObject obj=new JsonObject();
		obj.put("accFound", "true");
		obj.put("receiverAccNo", acc.getAccountNumber());
		obj.put("receiverName", acc.getFirstName()+" "+acc.getLastName());
		obj.put("receiverAge", acc.getAge());
		obj.put("receiverDOB", acc.getDob());
		try {
			PrintWriter out=res.getWriter();
			out.print(obj.toJson());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	protected void transferAccNotFound() {
		System.out.println("step 2");
	    res.setContentType("application/json");
	    JsonObject obj=new JsonObject();
	    obj.put("accFound", "false");
	    try {
	    	PrintWriter out=res.getWriter();
	    	out.print(obj.toJson());
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	}


	public void inSufficientFunds() {
		System.out.println("im here da insuffi");
	    res.setContentType("text/plain");
	    PrintWriter out;
		try {
			out = res.getWriter();
			out.print("inSufficientFunds");
		} catch (IOException e) {
			System.out.println("error in insuffients");
			e.printStackTrace();
		}  
	}

	public void amountSuccessfullySend(long amount, int accNo) {
		System.out.println("im da");
		res.setContentType("text/plain");
		try {
		PrintWriter out=res.getWriter();
		out.print("sent");
		}catch(Exception e) {
			System.out.println("error in success");
			e.printStackTrace();
		}
	}
}
