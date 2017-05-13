<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>ExchangeRates</title>
	<link rel="stylesheet" href="css/style.css"></head>
<body>
<form action="getCurrency" id="login"  method='POST'>
    <h1>Форма входа</h1>
    <fieldset id="inputs">
        <input id="username" name="userEmail" type="text" placeholder="Логин" autofocus required>   
        <input id="password" name="pass" type="password" placeholder="Пароль" required>
    </fieldset>
    <fieldset id="actions">
        <input type="submit" id="submit" value="ВОЙТИ">
        <a href="">Забыли пароль?</a><a href="register.jsp">Регистрация</a>
    </fieldset>
</form>
<p align="center"> 
</p>
</body>
</html>
