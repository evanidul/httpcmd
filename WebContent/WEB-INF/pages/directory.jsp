<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>

<!DOCTYPE HTML>

<html>
<head>
<title>Directory</title>
</head>
<body>

	
	<p>You are in: ${currentdir.name}</p>
	
	
	<p>${currentdir.name} has the following subdirectories:</p>
	
	<c:forEach var="subdir" items="${currentdir.subdirectories}">
		
		<p>${subdir.name}</p>
			
		
	</c:forEach>
	
	<p>Enter a command (ls|mkdir|cd|pwd)</p>
	
	<p>
   previous command = <%= request.getParameter("command")%>
	</p>
	
	<form action="DirectoryController" method="POST">
enter command: <input type="text" name="command">
<br />


</form>


</body>
</html>