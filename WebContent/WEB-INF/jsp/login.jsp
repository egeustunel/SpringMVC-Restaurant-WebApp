<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Login</title>
</head>
<body>
<form:form modelAttribute="user" >
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
               <td colspan = "2">
                  <input type = "submit" value = "login"/>
               </td>
            </tr>
         </table>  
      </form:form>
      ${message}
      <a href="home">Add Owner</a>
  
</body>
</html>