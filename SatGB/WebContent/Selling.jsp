<%@page import="com.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/main.js"></script>
</head>
<body>
	<div class="container">
	<div class="row"><div class="col-6">
	<h1>Products Management </h1>
	<form id="formItem" name="formItem">
		Item Name:
			<input id="Itemname" name="Itemname" type="text"
			class="form-control form-control-sm">
	<br> 
		Item Category:
			<input id="Itemcategory" name="Itemcategory" type="text"
			class="form-control form-control-sm">
	<br> 
		Item Price:
			<input id="Price" name="Price" type="text"
			class="form-control form-control-sm">
	<br> 
		Item Description:
			<input id="Discription" name="Discription" type="text"
			class="form-control form-control-sm">
	<br>
		<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
</form>

	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
<br>
	<div id="divItemsGrid">
	
<%
	Item itemObj = new Item();
	out.print(itemObj.readItems());	
%>
	
</div>
</div> </div> </div>
</body>
</html>