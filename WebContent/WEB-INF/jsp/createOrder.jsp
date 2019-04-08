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
<title>Order Creation</title>
</head>
<body>
<table>
	<tr>
			
			<th>Products</th>
			<th>ADD</th>
		</tr>
	<c:forEach items="${productList}" var="p">
	<tr>
		<form:form   modelAttribute="order"  method="post">
		<td>${p.getName()}</td>
		<td><input type="submit" name="Add" value="Add" /></td>
		<td><input type='hidden' name='productName' id='numero' value="${p.getName()}" /></td>	
		<td><input type='hidden' name='orderId' id='numero' value="${order.getId()}" /></td>	
		</form:form>
	</tr>
	</c:forEach>
</table>
	

</body>
</html>