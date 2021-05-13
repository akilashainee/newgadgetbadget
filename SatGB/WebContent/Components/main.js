
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
var status = validateItemForm(); 
if (status != true) 
 { 
 	$("#alertError").text(status); 
 	$("#alertError").show(); 
 	return; 
 } 
 
 // If valid------------------------
 var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT"; 
 
 $.ajax( 
 { 
	 url : "SellingAPI", 
	 type : type, 
	 data : $("#formItem").serialize(), 
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
 $("#hidItemIDSave").val($(this).data("ItemID")); 
 $("#Itemname").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#Itemcategory").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#Price").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#Discription").val($(this).closest("tr").find('td:eq(3)').text()); 
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
			 $("#divItemsGrid").html(resultSet.data); 
			 
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
		 $("#hidItemIDSave").val(""); 
		 $("#formItem")[0].reset(); 
}
 
//Delete 
$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
	url : "SellingAPI",
	type : "DELETE",
	data : "ItemID=" + $(this).data("ItemID"),
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
$("#divItemsGrid").html(resultSet.data);
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

	// Itemname
	if ($("#Itemname").val().trim() == "") 
    { 
 		return "Insert Itemname."; 
 	} 

	// Itemcategory------------------------
	if ($("#Itemcategory").val().trim() == "") 
	{ 
		return "Insert Itemcategory."; 
	}
	
	// PRICE-------------------------------
	if ($("#Price").val().trim() == "")
{
	return "Insert Price.";
}
	// is numerical value
	var tmpPrice = $("#Price").val().trim();
	if (!$.isNumeric(tmpPrice))
{
	return "Insert a numerical value for Price.";
}
	// convert to decimal price
$("#Price").val(parseFloat(tmpPrice).toFixed(2));

	
	// Discription------------------------
	if ($("#Discription").val().trim() == "") 
	{ 
		return "Insert Discription."; 
	}
	
return true; 
}

 