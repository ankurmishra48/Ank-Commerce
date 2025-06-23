<%@ page import="model.User" %>
<%
User auth = (User) session.getAttribute("auth");
if(auth == null){
    response.sendRedirect("login.jsp");
}
%>

<!DOCTYPE html>
<html>
<head>
    <title>Payment Success</title>
    <%@include file="includes/header.jsp" %>
</head>
<body>
<%@include file="includes/navbar.jsp" %>

<div class="container text-center mt-5">
    <h2 class="text-success">🎉 Payment Successful!</h2>
    <p>Thank you for your purchase, <strong><%=auth.getName()%></strong>.</p>
    <a href="orders.jsp" class="btn btn-primary mt-3">View Your Orders</a>
</div>

<%@include file="includes/footer.jsp" %>
</body>
</html>