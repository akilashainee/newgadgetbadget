$(document).ready(function()
{ 
		if ($("#alertSuccess").text().trim() == "") 
		{ 
			$("#alertSuccess").hide(); 
		} 
		$("#alertError").hide(); 
}); 

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
		 $("#alertSuccess").text(""); 
		 $("#alertSuccess").hide(); 
		 $("#alertError").text(""); 
		 $("#alertError").hide(); 
		 
// Form validation-------------------
	var status = validatepaymentForm(); 
	if (status != true) 
	{ 
		 $("#alertError").text(status); 
		 $("#alertError").show(); 
		 return; 
	} 
	
	// If valid------------------------
	var type = ($("#hidPIDSave").val() == "") ? "POST" : "PUT";
	$.ajax(
	{
	url : "paymentAPI",
	type : type,
	data : $("#formpayment").serialize(),
	dataType : "text",
	complete : function(response, status)
	{
	onpaymentSaveComplete(response.responseText, status);
	}
	});
	});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	// $("#hidIPIDSave").val($(this).closest("tr").find('#hidPIDUpdate').val()); 
	 $("#hidPIDSave").val($(this).data("PID"));
	 $("#amount").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#cardNo").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#cardName").val($(this).closest("tr").find('td:eq(2)').text()); 
	 $("#cardType").val($(this).closest("tr").find('td:eq(3)').text()); 
	 $("#expMonth").val($(this).closest("tr").find('td:eq(4)').text()); 
	 $("#expYear").val($(this).closest("tr").find('td:eq(5)').text()); 
	 $("#CVV").val($(this).closest("tr").find('td:eq(6)').text()); 
}); 
function onpaymentSaveComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully saved.");
$("#alertSuccess").show();
$("#divpaymentGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while saving.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while saving..");
$("#alertError").show();
}
$("#hidPIDSave").val("");
$("#formpayment")[0].reset();
}
$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
url : "paymentAPI",
type : "DELETE",
data : "PID=" + $(this).data("PID"),
dataType : "text",
complete : function(response, status)
{
onpaymentDeleteComplete(response.responseText, status);
}
});
});
function onpaymentDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divpaymentGrid").html(resultSet.data);
} else if (resultSet.status.trim() == "error")
{
$("#alertError").text(resultSet.data);
$("#alertError").show();
}
} else if (status == "error")
{
$("#alertError").text("Error while deleting.");
$("#alertError").show();
} else
{
$("#alertError").text("Unknown error while deleting..");
$("#alertError").show();
}
}

// CLIENT-MODEL================================================================
function validatepaymentForm() 
{ 
	// amount
	if ($("#amount").val().trim() == "") 
	{ 
		return "Insert amount."; 
	} 
// No
	if ($("#cardNo").val().trim() == "") 
	{ 
		return "Insert cardNo."; 
	} 
//name-------------------------------
	if ($("#cardName").val().trim() == "") 
	{ 
		return "Insert cardName."; 
	} 
// Type
	if ($("#cardType").val().trim() == "") 
	{ 
		return "Insert cardType."; 
	} 
 

// month------------------------
	if ($("#expMonth").val().trim() == "") 
 { 
		return "Insert expMonth."; 
 } 
	
	// year------------------------
	if ($("#expYear").val().trim() == "") 
 { 
		return "Insert expYear."; 
 } 
	
	// CVV-----------------------
	if ($("#CVV").val().trim() == "") 
 { 
		return "Insert CVV."; 
 } 
	
	
return true; 
}