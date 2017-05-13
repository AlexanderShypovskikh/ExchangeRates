package com.currencyAdviser.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBaseManager {
	private String dbURL;
	private String user;
	private String password;
	private String dbDriver;
	private static Connection connection = null;
	
	
	public DataBaseManager(String dbUrl, String dbUser, String dbPass, String dbDriver){
		try{ 
			  System.out.println("DB = "+dbDriver);
			  Class.forName("org.postgresql.Driver");
			 
		    } catch (ClassNotFoundException e) {
			     System.out.println("Where is your "+dbDriver+"Include in your library path!");
			      e.printStackTrace();
	         } 
		
		try{
			 connection = DriverManager.getConnection(dbUrl, dbUser,dbPass);
		}catch (SQLException e) {
		      	 System.out.println("Connection Failed! Check output console");
			      e.printStackTrace();
	     	}
		
		if (connection != null) 
			System.out.println("You made it, take control your database now!");
    else 
			System.out.println("Failed to make connection!");
		
    	}
	
	


	public static Connection getConnection(){
	
	 return connection;
	}
	

	
	public static synchronized boolean  validateUser(String userEmail, String userPass) throws SQLException {
		
		Statement stm = null;
		Connection connected = getConnection();
		
		if(connected != null){
			String query1 = "Select *  from users where email = '"+userEmail+"' and pass='"+userPass+"'";
			try{
			  System.out.println( query1);
				stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stm.executeQuery(query1);
				boolean b = rs.last();
            
				if(rs.getRow() > 0){
					return true;
				}
			}catch (SQLException e ) {
		       e.printStackTrace();
		    } finally {
		        if (stm != null) { stm.close(); }
		    }
			
		}
		return false;
	}
	
public static synchronized boolean  validateUserByEmail(String userEmail) throws SQLException {
		
		Statement stm = null;
		Connection connected = getConnection();
		
		if(connected != null){
			String query1 = "Select *  from users where email = '"+userEmail+"'";
			try{
			  System.out.println( query1);
				stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stm.executeQuery(query1);
				boolean b = rs.last();
            
				if(rs.getRow() > 0){
					return true;
				}
			}catch (SQLException e ) {
		       e.printStackTrace();
		    } finally {
		        if (stm != null) { stm.close(); }
		    }
			
		}
		return false;
	}
	public synchronized static  boolean addSessionId(String email, String sessionId) {
		Statement stm = null;
		Connection connected = getConnection();
		System.out.println("Session ID =="+sessionId);
		
		if (connection != null){
		String sql = "UPDATE users Set id_session = '"+sessionId+"' where email ='"+email+"'";
		System.out.println(sql);
		PreparedStatement pstmt = null;

	     
		try{
		System.out.println( sql);
		stm = connection.createStatement();
		 stm.executeUpdate(sql);

			
		}catch (SQLException e ) {
	       e.printStackTrace();
	       return false;
	    } finally {
	        if (pstmt != null) {
	        	try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Проблема в БД addSessionId ");
				}

	        }
	    }
		System.out.println("INSERTED new Session ID");
		return true;
	
		}
		return false;
    }
	
	public synchronized static  boolean deleteSessionId(String email, String sessionId) {
		Statement stm = null;
		Connection connected = getConnection();
		System.out.println("Session ID =="+sessionId);
		
		if (connection != null){
		String sql = "UPDATE users Set id_session = '' where email ='"+email+"'";
		System.out.println(sql);
		PreparedStatement pstmt = null;

	     
		try{
		System.out.println( sql);
		stm = connection.createStatement();
		 stm.executeUpdate(sql);

			
		}catch (SQLException e ) {
	       e.printStackTrace();
	       return false;
	    } finally {
	        if (pstmt != null) {
	        	try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Проблема в БД addSessionId ");
				}

	        }
	    }
		System.out.println("INSERTED new Session ID");
		return true;
	
		}
		return false;
    }
	
	public static synchronized boolean  validateUserBySession(String userEmail, String sessionId) throws SQLException{
		
		Statement stm = null;
		Connection connected = getConnection();
		
		if(connected != null){
			//Дописать условие валидации по Сессии;
			String query1 = "select * from users where email = '"+userEmail+
					"' and id_session='"+sessionId+"'";
			System.out.println(query1);
			try{
			    System.out.println( query1);
				stm = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stm.executeQuery(query1);
				boolean b = rs.last();
            
				if(rs.getRow() > 0){
					return true;
				}
			}catch (SQLException e ) {
		       e.printStackTrace();
		    } finally {
		        if (stm != null) { stm.close(); 
		        }
		    }
			
		}
		return false;
	}
	
	public static synchronized boolean addNewUser(String firstName, String lastName, String city,
			                  String email, String pass, String sessionId)throws SQLException {
		
		connection = getConnection();
		if(connection != null){

			 String sql = "INSERT INTO users(first_name, last_name, address,email,pass, id_session) "
			 		+ "VALUES (?,?,?,?,?,?)";
			 
			 PreparedStatement pstmt = null;
			 
			try{
			System.out.println( sql);
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setString(1, firstName);
			 pstmt.setString(2, lastName);
			 pstmt.setString(3, city);
			 pstmt.setString(4, email);
			 pstmt.setString(5, pass);
			 pstmt.setString(6, sessionId);
			 pstmt.executeUpdate();
				
			}catch (SQLException e ) {
		       e.printStackTrace();
		       return false;
		    } finally {
		        if (pstmt != null) {
		        	pstmt.close();
	
		        }
		    }
			return true;
		}
		
		return false; 
	}
	
	
	public static void closeConnection(){
	
		if (connection !=null){ 
		try{
	       connection.close();
		}catch(SQLException e){
            e.printStackTrace();			
 		}
		}
	}
	

}
