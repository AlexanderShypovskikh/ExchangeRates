package com.currencyAdviser.filters;

import java.io.IOException;
import java.net.HttpCookie;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.currencyAdviser.model.DataBaseManager;

/**
 * Servlet Filter implementation class UserSessionValidationFilter
 */
@WebFilter("/sessionFilter")
public class UserSessionValidationFilter implements Filter {
     private FilterConfig config;

	public UserSessionValidationFilter() {
        // TODO Auto-generated constructor stub
    }

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 System.out.println("USER SESSION VALIDATION");
		 
		 DataBaseManager dbManager = (DataBaseManager) request.getAttribute("dbDriver");
		 HttpServletRequest httpRequest  = (HttpServletRequest)request;
		 HttpServletResponse httpResponse = (HttpServletResponse)response;
		
		  String userEmail = getDataFromCookie(httpRequest.getCookies(), "user-name");
		  String sessionId = getDataFromCookie(httpRequest.getCookies(), "sessionId");
		  HttpSession session = httpRequest.getSession(false);
          
			 System.out.println("user-name="+userEmail);
			 if(httpRequest.getSession(false) != null)
			 System.out.println("sessionId from session="+httpRequest.getSession(false).getId());
			 
			 System.out.println("sessionId="+sessionId);
			 
			 String path = httpRequest.getRequestURI().toString();
			 System.out.println("Path = "+path);
			 System.out.println("___userEmail= "+userEmail +" HTTPRequest_URI"+httpRequest.getRequestURI().toString().equals("/currencyAdviser/currency.jsp"));
				
			 if(userEmail == null && httpRequest.getRequestURI().toString().equals("/currencyAdviser/currency.jsp")){
				// RequestDispatcher view = httpRequest.getRequestDispatcher("index.jsp");
			  //   view.forward(request, response);
				 System.out.println("userEmail= "+userEmail +" HTTPRequest_URI"+httpRequest.getRequestURI().toString().equals("/currencyAdviser/currency.jsp"));
				 httpResponse.sendRedirect("index.jsp");
			    	return;
			 }
				 
			 
		    if(userEmail == null ){
		    	chain.doFilter(request, response);
		    	return;
		    
		    } else if(userEmail != null){
		    	 System.out.println("sessionId= "+sessionId +" cookie user-name = "+userEmail);
			     try {
					if (dbManager.validateUserBySession(userEmail, sessionId)){	
						 System.out.println("User is validated by Session");
						// httpResponse.sendRedirect("currency.jsp");
						 request.setAttribute("session-validated", "true");
						 RequestDispatcher view = httpRequest.getRequestDispatcher("getCurrency");
					     view.forward(request, response);
						    return;
					 } else{
						// System.out.println("sessionId= "+sessionId +" cookie user-name = "+userEmail);
						   chain.doFilter(request, response);
					       return;		
					 }
				    }catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				    }

			     }
		}
		
		
	
    public String getDataFromCookie(Cookie[] cookie, String value){
    	Cookie[] cookies = cookie; 
    	if(cookie != null){
	    for(int i = 0; i < cookies.length; i++){
	    	if (cookies[i].getName().equals(value)){
	    		return cookies[i].getValue();
	    	}
	    }
    }
    	
	    return null;
     }
	

	public void destroy() {
		// TODO Auto-generated method stub
	}

}
