<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Customer Registration</title>
</head>
<body>

<h1> Please register to start shopping </h1>



<form  id="RegistertationForm" action="Register" method ="POST"> 

<input type ="text" name = "FirstName" placeholder ="FirstName" required > <br>
<input type ="text" name = "LastName" placeholder ="LastName" required ><br>
<input type ="text" name = "username" placeholder ="Username" required > <br>
<input type ="password" name = "password" placeholder ="Password" required > <br>

<input type ="submit" value ="Register"><br>

</form>


</body>
</html>