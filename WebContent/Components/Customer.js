$(document).ready(function()
{ 
	if ($("#alertSuccess").text().trim() == "") 
 	{ 
 		$("#alertSuccess").hide(); 
    } 
 	$("#alertError").hide(); 
});
 
// SAVE =========================================================================
$(document).on("click", "#btnSave", function(event) 
{ 
 // Clear alerts------------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 

// Form validation-------------------
var status = validateCustomerForm(); 
if (status != true) 
 { 
 	$("#alertError").text(status); 
 	$("#alertError").show(); 
 	return; 
 } 
 
 // If valid------------------------
 var type = ($("#hidIDSave").val() == "") ? "POST" : "PUT"; 
 
 $.ajax( 
 { 
	 url : "CustomerAPI", 
	 type : type, 
	 data : $("#formcustomer").serialize(), 
	 dataType : "text", 
	 complete : function(response, status) 
 	{ 
 		onItemSaveComplete(response.responseText, status); 
 	} 
 }); 
 });
 
// UPDATE========================================================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
 $("#IDSave").val($(this).data("itemid")); 
 $("#C_Name").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#C_Address").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#C_email").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#C_password").val($(this).closest("tr").find('td:eq(3)').text());
$("#C_ConNum").val($(this).closest("tr").find('td:eq(4)').text());  
});

//ItemSaveComplete function
 function onItemSaveComplete(response, status)
{ 

	if (status == "success") 
 	{ 
 		var resultSet = JSON.parse(response); 
 		if (resultSet.status.trim() == "success") 
		{ 
			 $("#alertSuccess").text("Successfully saved."); 
			 $("#alertSuccess").show(); 
			 $("#divCustomerGrid").html(resultSet.data); 
			 
 		} else if (resultSet.status.trim() == "error") 
 		{ 
			 $("#alertError").text(resultSet.data); 
			 $("#alertError").show(); 
 		} 
 	} else if (status == "error") 
 	{ 
		 $("#alertError").text("Error while saving."); 
		 $("#alertError").show(); 
 	}
 	 else
 	{ 
		 $("#alertError").text("Unknown error while saving.."); 
		 $("#alertError").show(); 
 	} 
		 $("#hidIDSave").val(""); 
		 $("#formcustomer")[0].reset(); 
}
 
//Delete 
$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
	url : "CustomerAPI",
	type : "DELETE",
	data : "ID=" + $(this).data("itemid"),
	dataType : "text",
	complete : function(response, status)
{
onItemDeleteComplete(response.responseText, status);
}
});
});
 
function onItemDeleteComplete(response, status)
{
if (status == "success")
{
var resultSet = JSON.parse(response);
if (resultSet.status.trim() == "success")
{
$("#alertSuccess").text("Successfully deleted.");
$("#alertSuccess").show();
$("#divCustomerGrid").html(resultSet.data);
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

 
 
 // CLIENT-MODEL=================================================================
function validateOrderForm() 
{ 

	// name
	if ($("#C_Name").val().trim() == "") 
    { 
 		return "Insert C_Name."; 
 	} 

	// Address------------------------
	if ($("#C_Address").val().trim() == "") 
	{ 
		return "Insert C_Address."; 
	}
	
	//Email------------------------------
	if ($("#C_email").val().trim() == "")
{
	return "Insert C_email";
}
	
	
	//Password-----------------------
	if ($("#C_password").val().trim() == "") 
	{ 
		return "Insert C_password"; 
	}
	//Phone number-----------------------
	if ($("#C_ConNum").val().trim() == "") 
	{ 
		return "Insert C_ConNum"; 
	}
	
	
return true; 
}

