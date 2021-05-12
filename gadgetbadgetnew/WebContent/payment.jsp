<%@page import="com.payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>Payment Management</title>
<link rel="stylesheet" href="views/bootstrap.min.css">
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/payment.js"></script>
</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-9">
				<h1>Payment Management</h1>
		<br>
	<form id="formpayment" name="formpayment" method="post" action="payment.jsp">
	
		 amount:
		 <input id="amount" name="amount" type="text"
 		 class="form-control form-control-sm">
 						
		<br> card No:
		<input id="cardNo" name="cardNo" type="text"
 		class="form-control form-control-sm">
 						
		<br> Card Name:
		<input id="cardName" name="cardName" type="text"
 		class="form-control form-control-sm">
 				
		<br> Card Type:
		<input id="cardType" name="cardType" type="text"
		class="form-control form-control-sm">
						 
		<br>expire Month:
		<input id="expMonth" name=" expMonth" type="text"
		class="form-control form-control-sm">
						 
		<br> expire Year :
		<input id=" expYear" name="expYear" type="text"
		class="form-control form-control-sm">
						 
		 <br> CVV :
		<input id="CVV" name="CVV" type="text"
		 class="form-control form-control-sm">		
						 	 
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save"
		class="btn btn-primary">
						 
		<input type="hidden" id="hidPIDSave" name="hidPIDSave" value="">
	</form>
	

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divpaymentGrid">

			<%
			payment payObj = new payment(); 
			 out.print(payObj.readpayment()); 
			%>
					</div>
				</div> 
			</div>
		</div>
	</body>
</html> 