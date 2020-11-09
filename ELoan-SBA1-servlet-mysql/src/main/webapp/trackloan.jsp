<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Track Loan</title>
</head>
<body>
	<!-- write html code to read the application number and send to usercontrollers'
             displaystatus method for displaying the information
	-->
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
	
	<!-- <div id="home" style="display:none">
	<p> Welcome Home</p>
	</div> -->
	
	<h2>Track Loan</h2>
	<form action="trackloan" method="post">
		<div>
				<label>Application No</label>
				<input type="number" name="applno" value="${loanInfo.applno }" 
				 required min="1" ${isNew?'':'readonly' } />
			</div>
		<div>
			<div><label for="purpose">Enter Loan purpose</label> </div>
			<div><input type="text" name="purpose" value="${loanInfo.purpose }"> </div>
		</div>
		<div>
			<div><label for="amtrequest">Enter requested Amount</label> </div>
			<div><input type="number" name="amtrequest" value="${loanInfo.amtrequest }"> </div>
		</div>
		<div>
			<div><label for="doa">Enter Date of Application</label> </div>
			<div><input type="date" name="doa" value="${loanInfo.doa }"> </div>
		</div>
		<div>
			<div><label for="email">Enter email </label> </div>
			<div><input type="text" id="email" value="${loanInfo.email }"> </div>
		</div>
		<div>
			<div><label for="mobile">Enter mobile number </label> </div>
			<div><input type="number" id="email" value="${loanInfo.mobile }"> </div>
		</div>
		<br>
		<div>
			<label for="status">Status: </label> 
			<input type="text" id="status" value="${loanInfo.status }">
		</div>
		
	</form>
	
<jsp:include page="footer.jsp"/>
</body>
</html>