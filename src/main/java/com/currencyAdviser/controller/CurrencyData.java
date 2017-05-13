package com.currencyAdviser.controller;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.currencyAdviser.model.CurrencyClass;
import com.currencyAdviser.model.CurrencylayerProvider;


/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/getCurrency")
public class CurrencyData extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private String endPoint = "live";
    private String accessKey = "YOUR_ACCESS_KEY";
    private Object dataObj = new Object();
    private CurrencylayerProvider cp; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurrencyData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		System.out.println("CURRRENCY DATA SERVLET is STARTING");
		HttpServletRequest request1 = request;
		cp = new CurrencylayerProvider();
		CurrencyClass currency = cp.getCurrencyData();
		request1.setAttribute("currencyData", currency);
		
		if(currency != null){
		RequestDispatcher view = 
			request1.getRequestDispatcher("currency.jsp");
		     view.forward(request1, response);
		     
		     return;
		}
		else{
			System.out.println("Currency = NULL"+ currency);
		}
		
	}

}
