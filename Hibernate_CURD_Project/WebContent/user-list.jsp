<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management Application</title>
</head>
<body bgcolor="orange">
	<center>
		<marquee>
			<font color="red">Welcome to User Management</font>
		</marquee>
		<h1>User Management</h1>
		<h2>
			<a href="new"> Add New User</a> &nbsp;&nbsp;&nbsp; <a href="list">List
				All Users</a>
		</h2>
	</center>
	<div align="center">
		<table border="1" cellopadding="5">
			<caption>
				<h2>List of Users</h2>
			</caption>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email</th>
				<th>Country</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="user" items="${listUser}">
				<tr>
					<td><c:out value="${user.id}" /></td>
					<td><c:out value="${user.name}" /></td>
					<td><c:out value="${user.email}" /></td>
					<td><c:out value="${user.country}" /></td>
					<td><a href=edit?id=<c:out value='${user.id}' />"> Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="delete?id=<c:out value='${user.id}' />">Delete</a></td>
				</tr>
			</c:forEach>


		</table>
	</div>
</body>
</html>