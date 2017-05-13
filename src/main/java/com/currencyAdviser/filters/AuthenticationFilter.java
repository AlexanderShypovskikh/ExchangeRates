package com.currencyAdviser.filters;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
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
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

     private FilterConfig config;

 	public void init(FilterConfig fConfig) throws ServletException {
 	  this.config = fConfig;
 	}
 	
 	
	public AuthenticationFilter() {}

	

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		  System.out.println("AUTH FILTER CALLED");
		  
		  DataBaseManager dbManager = (DataBaseManager) request.getAttribute("dbDriver");
		  String  userEmail = request.getParameter("userEmail");
		  String pass = request.getParameter("pass");
		  boolean isUser= false;
		 
		  HttpServletRequest httpRequest  = (HttpServletRequest)request;
	      HttpServletResponse httpResponse = (HttpServletResponse)response;
		  HttpSession session = httpRequest.getSession(false);
		  String Email = getUserEmail(httpRequest.getCookies());
		  System.out.println("user-name="+userEmail);

				      try {
	             		  isUser = dbManager.validateUser(userEmail, pass);
     	           	      } catch (SQLException e){ 
	         					e.printStackTrace();
	                        }  
		                   
	             		System.out.println("pass="+pass+" login="+userEmail+" isUser="+isUser);
	                      
	             		if(isUser){
	               			System.out.println("session isUser = "+isUser);
	   						initSession(httpRequest,httpResponse, userEmail);
	   						dbManager.addSessionId(userEmail, session.getId());
	      				     	System.out.println("User is validate and session created");
	             						chain.doFilter(request, response);
	             						return;
	             		}
	             		else {
	             			System.out.println("send Redirect");
	             			httpResponse.sendRedirect("index.jsp");
	             			return;
	             		}	
				 }
	//}
				
	//}


	public void initSession( HttpServletRequest httpRequest, HttpServletResponse httpResponse, String userEmail){
		HttpSession session = httpRequest.getSession();
		//System.out.println("user-email"+userEmail);
		session.setAttribute("user-name", userEmail);
	    Cookie cookieUserName = new Cookie("user-name", userEmail);
	    Cookie cookieSessionId = new Cookie("sessionId", session.getId());
	    cookieUserName.setMaxAge(2*60);
	    cookieSessionId.setMaxAge(2*60);
	    httpResponse.addCookie(cookieUserName);
	    httpResponse.addCookie(cookieSessionId);
	    System.out.println("cookieUsername = "+cookieUserName.toString());

	//    System.out.println("cookieSessionId = "+cookieSessionId.toString());
	    System.out.println("session = "+session);
	    System.out.println("session user-name = "+session.getAttribute("user-name"));
	    
	    
	    System.out.println("init session...");
		}
	
	 public String getUserEmail(Cookie[] cookie){
	    	Cookie[] cookies = cookie; 
		    for(int i = 0; i < cookies.length; i++){
		    	if (cookies[i].getName().equals("user-name")){
		    		return cookies[i].getValue();
		    	}
		    }
		    return null;
	     }
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		config = null;
	}
}
