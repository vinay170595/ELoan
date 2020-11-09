<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>user home</title>
</head>
<body>
	<!-- write the html code to display hyperlinks for user functionalities -->
<jsp:include page="header.jsp"/>
<hr>
<div>
<a href="userhome1.jsp">Welcome Home</a> <span>|</span>
<a href="application.jsp">Apply for Loan</a> <span>|</span>
<a href="trackloan.jsp">Track Loan Application</a> <span>|</span>
<a href="editloan.jsp">Edit Loan Application</a>
<a href="index.jsp" style="float:right">Logout</a>
</div>
<hr>

<div>
<h4> Welcome Home</h4>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>