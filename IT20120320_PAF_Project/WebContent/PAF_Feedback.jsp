<%@page import="model.PAF_Feedback"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/feedback.js"></script>
<title>Feedback details</title>
</head>
<body>
	<div class="container">
			<div class="row" style="justify-content: center;">
				<div class="col-8">
					<h1 class="m-3" style="text-align: center;font-size: 4rem;color: #800080;">Feedback details</h1>
					
					<form id="formFeedback" name="formFeedback">
						<!-- NAME -->
						<div class="form-group">
   							<label for="Name">Name</label>
   							<input type="text" class="form-control" id="F_Name" name="F_Name" placeholder="">
 						</div>
						
						<!-- CONTACT NO -->
						<div class="form-group">
   							<label for="ContactNo">Contact No</label>
   							<input type="tel" class="form-control" id="F_ContactNo" name="F_ContactNo" pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}">
 						</div>
 							
						<!-- EMAIL -->
						<div class="form-group">
   							<label for="Email">Email</label>
   							<input type="email" class="form-control" id="F_Email" name="F_Email" placeholder="name@example.com">
 						</div>
						
						<!-- MESSAGE -->
						<div class="form-group">
   							<label for="Message">Message</label>
   							<textarea class="form-control" id="F_Message" name="F_Message" rows="4"></textarea>
 						</div>
 						
						<input id="btnSave" name="btnSave" type="button" value="Send Feedback" class="btn btn-outline-success "> 
						<input type="hidden" id="hididSave" name="hididSave" value="">
						
					</form>
					
					<br>
					
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br> <br>
				</div>
				
				<div id="divFeedbackGrid">
						<%
							PAF_Feedback fdObj = new PAF_Feedback();
							out.print(fdObj.readFeedbacks());
						%>
				</div>
			</div>
		</div>
</body>
</html>