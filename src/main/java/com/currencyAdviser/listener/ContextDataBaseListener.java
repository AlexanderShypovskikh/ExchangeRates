package com.currencyAdviser.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.currencyAdviser.model.DataBaseManager;

/**
 * Application Lifecycle Listener implementation class ContextDataBaseListener
 *
 */
@WebListener
public class ContextDataBaseListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ContextDataBaseListener() {
        // TODO Auto-generated constructor stub
    }

	
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
        ServletContext ctx = servletContextEvent.getServletContext();
    	
    	String dbUrl = ctx.getInitParameter("dbURL");
    	String dbUser = ctx.getInitParameter("dbUser");
    	String dbPass = ctx.getInitParameter("dbPassword");
    	String dbDriver = ctx.getInitParameter("dbDriver");
    	
    	System.out.println("db Driver=" + dbDriver);
    	
    	//create database connection from init parameters and set it to context
    	DataBaseManager dbManager = new DataBaseManager(dbUrl, dbUser, dbPass, dbDriver);
    	if(dbManager != null){
    	ctx.setAttribute("DBManager", dbManager);
    	System.out.println("Database connection initialized for Application."+dbManager);
    	}else 
    		System.out.println("Cannot set Connection dbManager = "+dbManager);
    }
	
    
    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent)  { 
    	ServletContext ctx = servletContextEvent.getServletContext();
    	DataBaseManager dbManager = (DataBaseManager) ctx.getAttribute("DBManager");
    	dbManager.closeConnection();
    	System.out.println("Database connection closed for Application.");
    }

}
