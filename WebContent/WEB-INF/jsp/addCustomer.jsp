<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Add customer</title>
</head>
<body>
<h1>ADD CUSTOMER</h1>
<form:form modelAttribute="customer" >
         <table>
            <tr>
               <td>User Name : </td>               
               <td><form:input path="username"  /></td> 
            </tr>
            <tr>
               <td>Password : </td>
			   <td><form:input path="password"  /></td> 
            </tr>
            <tr>
               <td>Name : </td>               
               <td><form:input path="name"  /></td> 
            </tr>
            <tr>
               <td>Surname : </td>               
               <td><form:input path="surname"  /></td> 
            </tr>
            <tr>
               <td>Address : </td>               
<!--                <td><form:input path="surname"  /></td>  -->
            </tr>
            <tr>
               <td>Floor : </td>               
               <td><form:input path="address.floor"  /></td> 
            </tr>
             <tr>
               <td>Building : </td>               
               <td><form:input path="address.building"  /></td> 
            </tr>
             <tr>
               <td>Room : </td>               
               <td><form:input path="address.roomNo"  /></td> 
            </tr>
            
            <tr>
               <td colspan = "2">
                  <input type = "submit" value = "create"/>
               </td>
            </tr>
         </table>  
      </form:form>

	${message}
</body>
</html>