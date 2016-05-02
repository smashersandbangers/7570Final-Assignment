<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Registration</title>
<link rel="stylesheet" type="text/css" href="store.css">
</head>
<body>

	<h1>Please register to start shopping</h1>



	<form id="RegistertationForm" action="Register" method="POST">

		<input type="text" name="name" placeholder="Name" value="${name}" required><br>
		<input type="text" name="username" placeholder="Username" value="${username}" required> <br> 
	<input type="password" name="password" placeholder="Password" value="" required> <br> 
	<input type="submit" name="Register" value="Register"><br>

	</form>


	<% if(session.getAttribute("username")==null) {
			%>
	<a href="login.jsp">Login</a>
	<%} else {
				%>
	<a href="logout.jsp">Logout</a>
	<%}%>





</body>
</html>