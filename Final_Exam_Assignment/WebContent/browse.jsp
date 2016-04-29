
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@page import="model.Customer"%>
    <%
    String table= (String)request.getAttribute("table");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cricket Store</title>
</head>
<body>
<h1> Welcome ${Customer.FirstName} to the store please see the below catalogue to begin shopping</h1>




<table> 

<%= table%>

</table>
</body>
</html>