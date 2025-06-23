<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>User Registration</title>
    <%@include file="includes/header.jsp" %>
</head>
<body>
    <div class="container">
        <div class="card w-50 mx-auto my-5">
            <div class="card-header text-center">Register New Account</div>
            <div class="card-body">
                <form action="RegisterServlet" method="post">
                    <div class="form-group">
                        <label>Full Name</label>
                        <input type="text" class="form-control" name="name" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" name="email" required>
                    </div>
                    
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" class="form-control" name="password" required>
                    </div>
                    
                    <div class="text-center">
                        <button type="submit" class="btn btn-success">Register</button>
                        <a href="login.jsp" class="btn btn-link">Back to Login</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%@include file="includes/footer.jsp" %>
</body>
</html>
