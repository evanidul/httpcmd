<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>

<!DOCTYPE HTML>






<html>
  <head>
    <style>
      body {
        font-family: Arial;
        font-size: 12px;
      }
      div.container {
      	border: 1px solid #999;
      	height: 300px;
      	width: 100%;
      	position: relative;
      	overflow-y: hidden;
      	
      } 
      
      div.output {
        position: absolute;
        bottom: 20px;
        left: 0;
        border: 0px solid white;
        padding-left: 5px;
        
      }
      #form {
        position: absolute;
        bottom: 0;
        left: 0;
        margin-bottom: 0px;
        padding-left: 5px;
      }
      input {
        font-family: Arial;
        font-size: 12px;
        border: 1px;
        padding-left: 4px;
        width: 800px;
      }
      input:focus {
      	outline: 0;
      }
    </style>
  </head>
<body>
<%String cmdexecuted = (String)request.getAttribute("cmdexecuted"); %>
command executed = <%= cmdexecuted %> <br/>
<% String errormessage = (String)request.getAttribute("errormessage"); %>
error message =  <%= errormessage %>
<div class="container">
	<div class="output">
	<c:choose>
    	<c:when test="${not empty errormessage}">
        	${errormessage}
        	<br />
    	 </c:when>
    	<c:when test="${cmdexecuted.equals('ls')}">
    		<c:if test="${empty currentdir.subdirectories}">
				-- Empty --
			</c:if>
        	<c:forEach var="subdir" items="${currentdir.subdirectories}">		
				<p>${subdir.name}</p>
			</c:forEach>
        	<br />
    	</c:when>
    	<c:when test="${cmdexecuted.equals('cd')}">
        	OK.
        	<br />
    	</c:when>
    	<c:when test="${cmdexecuted.equals('mkdir')}">
        	OK.
        	<br />
    	</c:when>
    	<c:when test="${cmdexecuted.equals('pwd')}">
        	${currentdir.absoluteName}
        	<br />
    	</c:when>    
    	<c:otherwise>	        
	        <br />
    	</c:otherwise>
	</c:choose>
	
	
	</div>
	
	<form action="DirectoryController" method="POST" id="form">
		:<input class="input" type="text" name="command" id="cmd" maxlength="100" />
		<input type="hidden" name="sid" id="sid" value="" />

	</form>
 	
</div>
<br>
Commands: ls | mkdir | cd | pwd	
</body>

<script>
	document.getElementById("cmd").focus();
</script>


<p> Debug </p>
previous command = <%= request.getParameter("command")%> </br>




</html>



