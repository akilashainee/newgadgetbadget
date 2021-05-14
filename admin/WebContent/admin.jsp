<%@page import="com.admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Admin Management</title>
		<link rel="stylesheet" href="views/bootstrap.min.css">
		<script src="components/jquery.min.js"></script>
		<script src="components/admin.js"></script>
	</head>
	<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Admin Management </h1>
				<form id="formItem" name="formItem">
					Name:
					<input id="Name" name="name" type="text"
					class="form-control form-control-sm">
					<br> Address:
					<input id="Address" name="address" type="text"
					class="form-control form-control-sm">
					<br> Email:
					<input id="email" name="Email" type="text"
					class="form-control form-control-sm">
					<br> Password:
					<input id="password" name="Password" type="text"
					class="form-control form-control-sm">
					<br>
					Contact number:
					<input id="ConNum" name="number" type="text"
					class="form-control form-control-sm">
					<br> 
					<input id="btnSave" name="btnSave" type="button" value="Save"
					class="btn btn-primary">
					<input type="hidden" id="hidItemIDSave"
					name="hidItemIDSave" value="">
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divItemsGrid">
					<%
					admin itemObj = new admin();
					out.print(itemObj.readItems());
					%>
				</div>
			</div> 
		</div>
	</div>
	</body>
</html>