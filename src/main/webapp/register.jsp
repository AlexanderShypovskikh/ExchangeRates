<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Курс Валют</title>
	<link rel="stylesheet" href="css/style.css"></head>

<body>
<form action="registration" id='Register' method='POST'>
  <fieldset id="inputs">
    <label>Имя</label>
    <input type='text' name='name' placeholder='Имя' required><span></span>
    <br>
    <label>Фамилия</label>
    <input type='text' name='surname' placeholder='Фамилия' required><span></span>
    <br>
    <label>Город</label>
    <input type='text' name='city' placeholder='Город' required><span></span>
    <br>
    <label>Эл. почта</label>
    <input type='email' name='email' placeholder='Эл. почта' required><span></span>
    <br>
    <label>Пароль</label>
    <input type='password' name='pass' placeholder='Пароль' required><span></span>
    <br>
    <label>Повторите</label>
    <input type='password' name='repeat' placeholder='Повторите' required><span></span>
    <br>
  </fieldset>
  <!--  <fieldset >
    <p>Введите число с картинки</p>
    <img src="https://c22blog.files.wordpress.com/2010/10/input-black.gif">
    <input type='text' name='capcha' placeholder='число' required><span></span>
  </fieldset> -->
  <br>
     <fieldset id="actions">
        <input type="submit" id="submit2" value="Зарегистрироваться">
    </fieldset>
</form>
<br>
</body>
</html>
