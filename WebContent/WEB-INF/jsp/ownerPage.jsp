<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ page import="model.*"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Owner Page</title>
</head>
<body>
	<h1>Welcome ${currentOwner.getUsername()} !</h1>

	<h3>CUSTOMERS</h3>
		<a href="addCustomer">Add Customer</a>
	<table>
		<tr>

			<th>Username</th>
			<th>Password</th>
			<th>Name</th>
			<th>Surname</th>
			<th>Floor</th>
			<th>Building</th>
			<th>Room No</th>


		</tr>
	<c:forEach items="${customerList}" var="c">
<%-- 			<form action="/handleCustomer" method="post"> --%>
			<form:form method="post" modelAttribute="customer">
				<tr>
	
					<td>${c.getUsername()}</td>
					<td>${c.getPassword()}</td>
					<td>${c.getName()}</td>
					<td>${c.getSurname()}</td>
					<td>${c.getAddress().getFloor()}</td>
					<td>${c.getAddress().getBuilding()}</td>
					<td>${c.getAddress().getRoomNo()}</td>
					
					<td><input type="submit" name="Update" value="Update" /></td>
					<td><input type="submit" name="Delete" value="Delete" /></td>
					<td><input type='hidden' name='username' id='numero' value="${c.getUsername()}" /></td>
	
				</tr>
			</form:form>
		
		</c:forEach>
	</table>

		<h3>PRODUCTS</h3>
			<a href="addProduct">Add Product</a>
	<table>
		<tr>
			<th>Name</th>
			<th>Price</th>
		</tr>
			<c:forEach items="${productList}" var="p">
<%-- 			<form action="/handleCustomer" method="post"> --%>
<%-- 			<form:form action="crud" method="post" modelAttribute="customer"> --%>
				<form:form action="crud" method="post">
				<tr>
	

					<td>${p.getName()}</td>
					<td>${p.getPrice()}</td>
					
					<td><input type="submit" name="Update" value="Update" /></td>
					<td><input type="submit" name="Delete" value="Delete" /></td>
					<td><input type='hidden' name='productName' id='numero' value="${p.getName()}" /></td>
	
				</tr>
			</form:form>
		</c:forEach>
		
	</table>
	<h3>ORDERS</h3>
	<a href="createOrder">create Order</a>
	<table>
		<tr>
<!-- 			<th>ID</th> -->
			<th>Products</th>
			<th>Status</th>
		</tr>
		
		<c:forEach items="${orderList}" var="o">
		<form:form action="orderHandle" method="post">
<%-- 		<c:if test="${o.getProductList().size()!=0}"> --%>
		
			<tr>
			
					
<%-- 					<td>${o.id}</td> --%>
			
					<td>
					
					<p>${o.getProductName()}</p>
				
					</td>
					<td>${o.getStatus()}</td>
					
					<td><input type="submit" name="UpdateStatus" value="UpdateStatus" /></td>
<!-- 					<td><input type="submit" name="AddProduct" value="AddProduct" /></td> -->
					<td><input type='hidden' name='orderId' id='numero' value="${o.getId()}" /></td>
			</tr>
<%-- 		</c:if>	 --%>
		</form:form>
		</c:forEach>
		
	</table>
	

</body>
</html>