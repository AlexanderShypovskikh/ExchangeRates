package com.currencyAdviser.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
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
 * Servlet Filter implementation class LogOutFilter
 */
@WebFilter("/logOut")
public class LogOutFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LogOutFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * 
	 * 			
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	   
		System.out.println("LOG OUT  FILTER CALLED");
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
	
		DataBaseManager dbManager = (DataBaseManager) request.getAttribute("dbDriver");
		  HttpSession session = httpRequest.getSession(false);
		  String Email = getUserEmail(httpRequest.getCookies());
		dbManager.deleteSessionId(Email, session.getId());
		Cookie sessionId = new Cookie("sessionId", null);
      	Cookie userName = new Cookie("user-name", null);
		sessionId.setMaxAge(0);
		userName.setMaxAge(0);
      	
			  httpResponse.addCookie(sessionId);
			  httpResponse.addCookie(userName);
			  
		
		        httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");
		

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
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
