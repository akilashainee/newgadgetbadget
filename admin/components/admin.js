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
url : "adminAPI",
type : type,
data : $("#formItem").serialize(),
dataType : "text",
complete : function(response, status)
{
onItemSaveComplete(response.responseText, status);
}
});
});
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
$("#hidaccountIDSave").val($(this).data("accountid"));
$("#Name").val($(this).closest("tr").find('td:eq(0)').text());
$("#Address").val($(this).closest("tr").find('td:eq(1)').text());
$("#email").val($(this).closest("tr").find('td:eq(2)').text());
$("#password").val($(this).closest("tr").find('td:eq(3)').text());
$("#ConNum").val($(this).closest("tr").find('td:eq(4)').text());
});

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
} else
{
$("#alertError").text("Unknown error while saving..");
$("#alertError").show();
}
$("#hidItemIDSave").val("");
$("#formItem")[0].reset();
}
$(document).on("click", ".btnRemove", function(event)
{
$.ajax(
{
url : "adminAPI",
type : "DELETE",
data : "accountID=" + $(this).data("accountid"),
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
// CLIENT-MODEL================================================================
function validateItemForm()
{
// CODE
if ($("#Name").val().trim() == "")
{
return "Insert name.";
}
// NAME
if ($("#Address").val().trim() == "")
{
return "Insert Address.";
}
// PRICE-------------------------------
if ($("#email").val().trim() == "")
{
return "Insert email.";
}

// DESCRIPTION------------------------
if ($("#password").val().trim() == "")
{
return "Insert password.";
}

// DESCRIPTION------------------------
if ($("#ConNum").val().trim() == "")
{
return "Insert Contact number.";
}
return true;
}