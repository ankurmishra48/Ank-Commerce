<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="model.User" %>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        response.sendRedirect("index.jsp");
    }
%>

<%
    java.util.ArrayList<model.Cart> cartlist = (java.util.ArrayList<model.Cart>) session.getAttribute("cart-list");
    if (cartlist == null) {
        cartlist = new java.util.ArrayList<>();
    }
    session.setAttribute("cartlist", cartlist);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Login</title>
    <%@include file="includes/header.jsp"%>
</head>
<body>
    <div class="container">
        <div class="card w-50 mx-auto my-5">
            <div class="card-header text-center">User Login</div>
            <div class="card-body">
                <form action="LoginServlet" method="post">
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" name="login-email" placeholder="Enter your email" required>
                    </div>

                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" class="form-control" name="login-password" placeholder="*****" required>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Login</button>
                    </div>
                </form>

                <!-- ✅ Register Now Button -->
                <div class="text-center mt-3">
                    <p>Don't have an account?</p>
                    <a href="register.jsp" class="btn btn-outline-secondary">Register Now</a>
                </div>
            </div>
        </div>
    </div>

    <%@include file="includes/footer.jsp"%>
</body>
</html>
