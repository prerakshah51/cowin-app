<html>
<head>
<title>Result</title>
</head>
<body>
	<a href="../user.jsp">Back to home</a>
	<br>
	<br>
	<%
		out.print(request.getAttribute("resultStr"));
	%>
</body>
</html>
