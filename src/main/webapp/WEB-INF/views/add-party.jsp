<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<p>Navigation:
<a href="/">Back to Party List</a></p>
 <h1>Add a Party</h1>
 <form action="/submit-party">
 	<label>Name:</label>
 	<input type="text" name="name"/>
 	<label>Date:</label>
 	<input type="date" name="date"/>
 	<button type="submit">Submit</button>
 </form>
 </div>
</body>
</html>