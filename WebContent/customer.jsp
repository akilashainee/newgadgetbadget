<%@page import="com.Cus1"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>customer Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/Customer.js"></script>
</head>
<body>
	<div class="container">
	<div class="row"><div class="col-6">
	<h1>Customer Management </h1>
	<form id="formcustomer" name="formcustomer">
		Customer Name:
			<input id="Customer Name" name="Customer Name" type="text"
			class="form-control form-control-sm">
	<br> 
		Address:
			<input id="Address" name="Address" type="text"
			class="form-control form-control-sm">
	<br> 
		Email:
			<input id="Email" name="Email" type="text"
			class="form-control form-control-sm">
	<br> 
		Password:
			<input id="Password" name="Password" type="text"
			class="form-control form-control-sm">
			
			<br> 
		Phone Number:
			<input id="Phone Number" name="Phone Number" type="text"
			class="form-control form-control-sm">
	<br>
		<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>

	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
<br>
	<div id="divCustomerGrid">
	
<%
	Cus1 itemObj = new Cus1();
	out.print(itemObj.readItems());	
%>
	
</div>
</div> </div> </div>
</body>
</html>