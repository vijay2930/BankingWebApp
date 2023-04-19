package com.login;

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
 * Servlet implementation class UserDetailServlet
 */
@WebServlet("/UserDetailServlet")
public class UserDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserDetailServlet() {
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
		System.out.println("in user details");
		// TODO Auto-generated method stub
		response.setContentType("application/json");
	    PrintWriter out=response.getWriter();
	    HttpSession obj=request.getSession(); 
	    Account account=(Account)obj.getAttribute("acc");
	    JsonObject object=new JsonObject();
	    JsonObject parentObj=new JsonObject();
	    object.put("name", account.getFirstName()+" "+account.getLastName());
	    object.put("accNo",account.getAccountNumber());
	    object.put("DOB",account.getDob());
	    object.put("accNo",account.getAccountNumber());
	    object.put("age",account.getAge());
	    parentObj.put("accDetails", object);
	    System.out.println(parentObj);
	    out.print(parentObj.toJson());
	}

}
