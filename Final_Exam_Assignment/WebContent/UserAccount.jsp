
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cricket Store</title>

<link rel="stylesheet" type="text/css" href="store.css">
</head>
<body>
	<h1>Welcome ${sessionScope.name} to the  the Cricket store, USA's Number one sporting goods store for Cricket gear
	 please see  you account details below </h1>

Name: ${sessionScope.name} <br>
Username: ${sessionScope.userName};
	
<p>
<a href="Login?logout=true">Logout</a>
<a href = "browse" > Browse</a>

</body>
</html>