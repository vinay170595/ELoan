<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<div align=center>
	<h2>eLoan Registration</h2>
	<form action="registernewuser" method="post">
		<div>
			<div><label for="loginid">Enter login Id</label> </div>
			<div><input type="text" id="loginid" name="username"> </div>
		</div>
		<div>
			<div><label for="password">Enter password</label> </div>
			<div><input type="password" id="password" name="password"> </div>
		</div>
		<c:if test="${err!=null }">
			<p><strong style="color:red;">${err}</strong></p>
		</c:if>
		<div>
			<div><input type="submit" value="Register"> </div>
		</div>
		<a href="index.jsp">Back to Login</a>
	</form>
</div>
<hr/>
<jsp:include page="footer.jsp"/>
</body>
</html>