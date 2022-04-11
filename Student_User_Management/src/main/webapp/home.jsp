<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
<style type="text/css">
header {
	background-color: darkorange;
	text-align: right;
	color: white;
	font-size: 30px;
}

footer {
	text-align: center;
	background-color: orange;
	color: white;
	font-size: 30px;
}

section {

	height: 490px;
}
</style>
</head>
<body bgcolor="liteblue" type="text/css">
	<form action="/" method="post">
		<header>
			<nev>
			<menu>
				<span><a href="home.jsp">Home</a></span>
				<span> | </span>
				<span><a href="about.html">About</a></span>
				<span> | </span>
				<span> <a href="studentuser-form.jsp">Student Form</a></span>
				<span> | </span>
				<span><a href="studentuser-list.jsp">Student List</a></span>
			</menu>
			</nev>
		</header>
		<section>
			<marquee>
				<font color="red" ><h1>Welcome To My World..</h1></font>
			</marquee>
		</section>
		<footer align="center"> &copy; This WebSite Belongs to Kick.. </footer>
	</form>
</body>