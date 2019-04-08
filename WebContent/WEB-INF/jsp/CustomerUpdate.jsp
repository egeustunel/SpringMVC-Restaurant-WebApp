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
<title>Update Info</title>
</head>
<body>

<form:form action="CustomerUpdate" modelAttribute="customerForUp" method="post" >
<input type='hidden' name='id' id='numero' value="${customerForUp.getUsername()}" />

		Username:<form:input type="text" value="${customerForUp.getUsername() }" name="username" path="username" /><br /> 

		Password:<form:input type="text" value="${customerForUp.getPassword() }" name="password" path="password" /><br /> 
		Name:<form:input type="text" value="${customerForUp.getName() }" name="name" path="name"/><br /> 
		Surname:<form:input type="text" value="${customerForUp.getSurname() }" name="surname" path="surname"/><br /> 
		Floor:<form:input type="number" value="${customerForUp.getAddress().getFloor() }" name="address.floor" path="address.floor"/><br />
		Building:<form:input type="text" value="${customerForUp.getAddress().getBuilding() }" name="address.building" path="address.building" /><br /> 
		Room No:<form:input type="number" value="${customerForUp.getAddress().getRoomNo() }" name="address.roomNo" path="address.roomNo" /><br />  
		
		<input type="submit" name="update2" value="Update">
		

	</form:form>
</body>
</html>