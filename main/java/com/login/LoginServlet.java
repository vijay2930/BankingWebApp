package com.login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.dto.Account;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	private HttpServletRequest req;
	private HttpServletResponse res;
	public LoginServlet() {
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
		LoginController lc = new LoginController(this);
		PrintWriter out = response.getWriter();
		res=response;
		req=request;
		if (request.getParameter("userName") == null) {
			StringBuilder sb=new StringBuilder();
			BufferedReader buffer=request.getReader();
			String line;
			while((line =buffer.readLine())!=null) {
				ObjectMapper obj=new ObjectMapper();
				JsonNode data=obj.readTree(line);
				System.out.println(data.get("fName").asText());
				System.out.println(data.get("lName").asText());
				System.out.println(data.get("password").asText());
				System.out.println(data.get("age").asInt());
				System.out.println(data.get("address").asText());
				System.out.println(data.get("nationality").asText());
				System.out.println(Long.parseLong( data.get("adhaarNo").asText()));
				Account account=new Account(data.get("fName").asText(), data.get("lName").asText(), data.get("password").asText(), data.get("age").asInt(), data.get("address").asText(), data.get("nationality").asText(), data.get("dob").asText(),Long.parseLong( data.get("adhaarNo").asText()));
				lc.createAccount(account);
				break;
			}

		} else {
			// TODO Auto-generated method stub
			try {
				Account acc = lc.checkUser(Integer.parseInt(request.getParameter("userName")),
						request.getParameter("password"));
				response.setContentType("application/json");
				if (acc != null) {
					out.write(acc.getAccountNumber());
					HttpSession session = request.getSession(true);
					session.setAttribute("acc", acc);
					System.out.println("im here");
				} else {
					out.write("not found");
				}
			} catch (Exception e) {
				System.out.println(request.getParameter("userName") + request.getParameter("password"));
				e.printStackTrace();
				// send an error response to the client
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				response.getWriter().append("An error occurred while processing your request.");
			}
		}
	}

	public void accountCreationSuccess(int accountNo) {
		// TODO Auto-generated method stub
		try {
			PrintWriter out=res.getWriter();
			res.setContentType("text/plain");
			out.write("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
