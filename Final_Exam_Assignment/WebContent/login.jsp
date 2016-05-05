<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cricket Store Online Login Form</title>
<link rel="stylesheet" type="text/css" href="store.css">
</head>
<body>
	<h1>Cricket Store Online</h1>

<div id="nav"> 
                <ul>
                    <li><a href="login.jsp"> Login </a></li>
                    <li><a href="registration.jsp"> Register</a></li>
                   </ul>
                   </div>



	<div id="center">

		<img class="login-image"
			src="http://static.wixstatic.com/media/c32172_5aa2c4c9c6cf47ca00721b858a3ae8e9.jpg"
			alt="Mainpic" />

	</div>
	

	<p>Please enter your username and password to continue shopping</p>

	<form id="loginForm" action="Login" method="POST">

		<input type="text" name="username" placeholder="Username" required>
		<br> <input type="password" name="password" placeholder="Password" required> <br> 
		<input type="submit" value="login"><br>
		
	</form>
	${message}
	
</body>
</html>