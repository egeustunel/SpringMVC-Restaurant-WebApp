<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Customer Page</title>
</head>
<body>
<p>Customer Page</p>
<h1>Welcome ${currentCustomer.getName()} !</h1>

<a href="updateOwnInfo">Update</a>

<h3>ORDERS</h3>
	<a href="createOrder">create Order</a>
	<table>
		<tr>
<!-- 			<th>ID</th> -->
			<th>Products</th>
			<th>Status</th>
		</tr>
		
		<c:forEach items="${currentCustomer.getOrderList()}" var="o">
		<form:form action="orderHandle" method="post">
<%-- 		<c:if test="${o.getProductList().size()!=0}"> --%>
		
			<tr>
			
					
<%-- 					<td>${o.id}</td> --%>
			
					<td>
					
					<p>${o.getProductName()}</p>
					
					</td>
					<td>${o.getStatus()}</td>
					
<!-- 					<td><input type="submit" name="UpdateStatus" value="UpdateStatus" /></td> -->
<!-- 					<td><input type="submit" name="AddProduct" value="AddProduct" /></td> -->
					<td><input type='hidden' name='orderId' id='numero' value="${o.getId()}" /></td>
			</tr>
<%-- 		</c:if>	 --%>
		</form:form>
		</c:forEach>
		
	</table>
<%-- <form:form method="post" > --%>

<!-- <input type="submit" name="Update" value="Update" /> -->
<%-- <input type='hidden' name='username' id='numero' value="${currentCustomer.getUsername()}" /> --%>
<%-- </form:form> --%>
</body>
</html>