<%@ page language="java" contentType="text/html;charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.currencyAdviser.model.CurrencyClass" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Exchange Rates</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<%
  HttpSession userSession =  request.getSession(false); 
  %>
   <div class="container">
      <div class="header"> 
           <div class="header_inner" ></div>
           <div class="header_inner">Hello 
              <%=userSession.getAttribute("user-name") %>
              <% System.out.println(userSession.getAttribute("user-name")); %>
           (user-role)</div>
           <div class="header_inner" ><a href="logOut">Выйти</a href></div>
       </div>
      <div class="content">    
     <div>
     
     <table  border ="3px" align="center" width="300px" height="100%">
     <tr><th  colspan="2">Курсы валют по отношению к доллару</th></tr>
     <tr><td align="center">Валюта</td><td align="center">Курс</td></tr>
    <% CurrencyClass currency = (CurrencyClass)request.getAttribute("currencyData");
     if(currency.isSuccess() == true){
     Map<String, String> map =  currency.getQuotes();
    
     for(Map.Entry<String, String> elem : map.entrySet()){ %>
        <tr> <td align="left"> <%= elem.getKey() %></td>
              <td align="center"> <%= elem.getValue() %> </td></tr>
        <% } } else {response.getWriter().println("Sorry but currently data is not avaliable");} %>
    </table>
      </div>
    <div class="footer"></div>
 </div>
</body>
</html>