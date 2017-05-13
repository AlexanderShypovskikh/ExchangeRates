package com.currencyAdviser.filters;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class UserSessionFilter
 */
@WebFilter("/UserSessionFilter")
public class ValidateSessionFilter implements Filter {
	private FilterConfig config;
	private ServletContext sContext;

	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
		sContext = this.config.getServletContext();
		sContext.log(this.config.getFilterName() +"ValidateSession" );
		
		// TODO Auto-generated method stub
	}

    public ValidateSessionFilter() {
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		 System.out.println("VALIDATE SESSION  CALLED");
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		HttpSession session = request.getSession(false);
		System.out.println("Sesion is Here");
		if(session == null){	
			System.out.println("Sesion is null");
			chain.doFilter(request, response);
		}
		  else {
			  System.out.println("Sesion is NOT null = "+session);
			RequestDispatcher rd = request.getRequestDispatcher("/getCurrency"); 
        	rd.forward(req, resp);
        
        }
	}



	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		config = null;
		sContext = null;
	}
}
