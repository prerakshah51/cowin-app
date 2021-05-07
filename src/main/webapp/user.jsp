<html>
<head>
<title>Vaccine Slot Notification</title>
</head>
<body>
	<h2>Cowin Slot Notification</h2>
	<form action="<%=request.getContextPath()%>/cowin/saveuser"
		method="POST">
		Name: <input type="text" name="name"><br> <br>
		District: <input type="text" name="district"><br> <br>
		Pincode: <input type="text" name="pincode"><br> <br>
		Email: <input type="text" name="email"><br> <br>
		Mobile: <input type="text" name="mobile"><br> <br> <br>
		<input type="submit" value="Save and check available appointments">
		<button type="submit"
			formaction="<%=request.getContextPath()%>/cowin/startjob">Start
			job</button>
	</form>
</body>
</html>
