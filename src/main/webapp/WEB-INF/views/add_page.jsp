<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bhardwaj's Library</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ"
	crossorigin="anonymous">
<link rel="stylesheet" href="<c:url value="/resources/css/add.css" />">

</head>
<body>
	<div class="container upper-container">
		<div class="fluid-container header">
			<div class="row pt-3 pb-3">
				<div class="col-md-4 col-sm-6"></div>
				<div class="col-md-4 col-sm-6 text-center">
					<H3>Bhardwaj's Online Library</H3>
				</div>
				<div class="col-md-4 col-sm-6" id="log-out-btn">
					<b>Welcome <c:out value="${loggeduser }"></c:out></b> <a href="logout" class="btn btn-outline-primary">log
						out</a>
				</div>
			</div>
		</div>
		<hr />

		<div class="fluid-container content">

			<div class="row pt-3 pb-3">
				<div class="col-12 text-center">
					<h4>Add Book Details</h4>
				</div>
			</div>
			<div class="row">
				<div class="col-8">
					<table>
						<tr>
							<td>Book Code</td>
							<td><input type="text" name="bookCode" id="bookCode" required="required"></td>
						</tr>
						<tr>
							<td>Book Name</td>
							<td><input type="text" name="bookName" id="bookName" required="required"></td>
						</tr>
						<tr>
							<td>Author</td>
							<td><select id="bookAuthor">
									<c:forEach var="author" items="${authorList}">
										<option value="${author.getAuthorId()}"><c:out
												value="${author.getAuthorName()}"></c:out></option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<td>Added On</td>
							<td id="dateAdded"></td>
						</tr>

					</table>
					<div class="mt-4">
						<span class="btn btn-primary buttons" id="addNewBook">Submit</span> <a href="home"
							class="btn btn-danger buttons">Cancel</a>

					</div>
				</div>

			</div>


		</div>






	</div>
	<div class="container-fluid footer">

		<div class="container lower-container">

			<h6 class="text-center">Copyright 2023-24 by Fresher Training
				Team</h6>

		</div>

	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
		crossorigin="anonymous"></script>
	<script src="<c:url value="/resources/js/add.js" />"></script>

</body>
</html>