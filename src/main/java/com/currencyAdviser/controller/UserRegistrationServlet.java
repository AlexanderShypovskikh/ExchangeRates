package com.currencyAdviser.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.currencyAdviser.model.DataBaseManager;


/**
 * Servlet implementation class AddNewUserServlet
 */
@WebServlet("/AddNewUser")
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	private String firstName;
	private String lastName;
	private String email;
	private String city;
	private String pass;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegistrationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 firstName = request.getParameter("name");
		 lastName = request.getParameter("surname");
		 email = request.getParameter("email");
		 city = request.getParameter("city");
		 pass = request.getParameter("pass");
		 System.out.println(firstName+""+lastName+""+email+""+city+""+pass);
		 DataBaseManager dbManager = (DataBaseManager) request.getAttribute("dbDriver");
		 
		// dbProvider = DataBaseProvider.getInstance(userEmail, pass, dbManager);
		 try{
			boolean isUser =  dbManager.validateUserByEmail(email);
			if(isUser){
				System.out.println("Такой пользователь уже существует");
				response.sendRedirect("index.jsp");
				return;
			}
		 }catch(SQLException e){
			 e.printStackTrace();
		 }
		 
		 try {
			 HttpSession session = request.getSession();
			 session.setAttribute("user-name", email);
			boolean isAdd = dbManager.addNewUser(firstName, lastName, city, email, pass, session.getId());
			if (isAdd){
				 System.out.println("User added");
				 RequestDispatcher v = request.getRequestDispatcher("/getCurrency");
				v.forward(request, response);
			}
			else {
				System.out.println("User is NOT added");
				response.sendRedirect("index.jsp");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
