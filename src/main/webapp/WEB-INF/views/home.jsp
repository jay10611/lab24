<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <div class="container">
     <h1>Parties</h1>
      <p>Navigation:
     <a href="/partyoption"> Options&Votes</a>
     <a href="/review-page">Review Options</a>
     <a href="/admin-page">Admin</a></p>
 
     <h2>Search</h2>
     <form action="search-name">
     	<label>By Name</label>
     	<input type="text" name="name"/>
     	<button type="submit">Search</button>
     	     </form>
     	     <ul>
 			<c:forEach var="partytype" items ="${party}" >
 				<li><c:out value="${partytype.name}"/> -
 				<c:out value="${partytype.date}"/>
 				<a href="<c:url value="${partytype.id}"/>">See Details</a><br>
 				<p>RSVPs: ${fn:length(partytype.rsvps)}</p>
 				</li>
 			</c:forEach>
     
     </ul>
     <br>
     <h1>RSVP</h1>
     <form action="/save-rsvp">
     <label>Party</label>
     	<select name="party">
     	<c:forEach var="party" items ="${party}">
     	<option value="${party.id}">
     	<c:out value="${party.name}"/>
     	</option>
     	</c:forEach>
     	</select><br><br>
     	<label>Attendee</label>
     	<input type="text" name="attendee"/><br><br>
     	<label>Comment</label>
     	<textarea name="comment" rows="4" cols="30">
     	</textarea>
     	<button type="submit">Submit</button>
     </form>
     
     
       </div>
</body>
</html>