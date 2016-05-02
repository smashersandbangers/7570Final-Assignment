
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
    String table= (String)request.getAttribute("table");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cricket Store</title>
<link rel="stylesheet" type="text/css" href="store.css">
</head>
<body>
	<h1> ${sessionScope.name} please confirm the items in your cart </h1>
	<br>
	
<br>
	
<table>
<thead>
<tr>
<th>Product Name</th>
<th>Image</th>
<th>Product Description</th>
<th>Price</th>
<th>Quantity</th>
<th>Action</th>
</thead>
<tbody><%= table%></tbody>
</table>
	<table>
	
	<td> Total Price:$${totalPrice}</td>
	
	</table>
	
	
	<table> 
	
	<tr>
	<td>
	 <a href="Login?logout=true"> Log out</a></td> 
	 
	  <td> <a href="Delete"> Delete</a></td>  
	  
	 <td> <a href="browse"> Continue Shopping</a></td> 
	 </tr>
</table>
	
	
	
</body>
</html>