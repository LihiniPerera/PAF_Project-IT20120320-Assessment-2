$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});

//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide();

	// Form validation-------------------
	var status = validateFeedbackForm();

	if (status != true)
	 {
		 $("#alertError").text(status);
		 $("#alertError").show();
		 return;
	}
	
	var type = ($("#hididSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
		{
		 url : "PAF_FeedbackAPI",
		 type : type,
		 data : $("#formFeedback").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 	onFeedbackSaveComplete(response.responseText, status);
		 }
		});

});	

function onFeedbackSaveComplete(response, status)
{
	if (status == "success")
	 {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divFeedbackGrid").html(resultSet.data);
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
	$("#hididSave").val("");
	$("#formFeedback")[0].reset();
}

//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	 $("#hididSave").val($(this).closest("tr").find('#hididUpdate').val());
	 $("#F_Name").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#F_ContactNo").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#F_Email").val($(this).closest("tr").find('td:eq(2)').text());
	 $("#F_Message").val($(this).closest("tr").find('td:eq(3)').text());

});


$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
	{
		 url : "PAF_FeedbackAPI",
		 type : "DELETE",
		 data : "F_ID=" + $(this).data("itemid"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 	onFeedbackDeleteComplete(response.responseText, status);
		 }
	});
});

function onFeedbackDeleteComplete(response, status)
{
	if (status == "success")
	 {
	 	var resultSet = JSON.parse(response);
	
	 	if (resultSet.status.trim() == "success")
	 	{
	 		$("#alertSuccess").text("Successfully deleted.");
	 		$("#alertSuccess").show();
	 		$("#divFeedbackGrid").html(resultSet.data);	
					
	 	}else if (resultSet.status.trim() == "error")
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

//CLIENTMODEL validate
function validateFeedbackForm()
{
	
	//first_name
	if ($("#F_Name").val().trim() == "")
	{
	return "Insert name.";
	}
	
	//Contact Number
	if ($("#F_ContactNo").val().trim() == "")
	{
	return "Insert Contact Number.";
	}

	//Email	
	function isEmail(F_Email) {
		var regex =  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/; 
			return regex.test(F_Email);
	}


	if ($("#F_Email").val().trim() == "")
	{
	return "Insert Email.";
	}
	else if(!isEmail($("#F_Email").val())){
		return "Insert valid Email";
	}

	//Message
	if ($("#F_Message").val().trim() == "")
	{
	return "Insert Messagee.";
	} 
	
	return true;
}